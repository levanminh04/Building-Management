package com.javaweb.repository.custom.impl;


import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.BuildingRepositoryCustom;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl  implements BuildingRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public void joinTable(BuildingSearchBuilder buildingSearchBuilder, StringBuilder sql) {

        Long staffid = buildingSearchBuilder.getStaffId();

        // kiểu Long chỉ cần check != null là được rồi
        if (staffid != null) {
            sql.append("JOIN assignmentbuilding a ON b.id = a.buildingid ");
        }
    }

    // để duyệt một đối tượng (trong trường hợp này ta cần duyệt buildingSearchBuilder), ta cần biết đến khái niệm java reflection
    public void normalQuery(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {

        try {
            Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
            for(Field item : fields) {
                item.setAccessible(true); // phải có lệnh này thì nó mới cho lấy dữ liệu
                String fieldName = item.getName(); // trả về tên của thuộc tính như được định nghĩa trong class
                if (!fieldName.equals("staffId") && !fieldName.equals("typeCode")
                        && !fieldName.startsWith("min") && !fieldName.startsWith("max")) 	{
                    Object value = item.get(buildingSearchBuilder); // Phương thức get() của Field trong Java Reflection luôn trả về giá trị dưới dạng Object, bất kể kiểu dữ liệu gốc của thuộc tính đó là kiểu gì., không nên dùng toString() ở dòng này vì nếu giá trị là null thì toString() sẽ bị NullPointerException
                    if( value != null ) {
                        if(item.getType().getName().equals("java.lang.Long")
                                || item.getType().getName().equals("java.lang.Integer") ) { // getType(): Trả về kiểu dữ liệu của một field dưới dạng đối tượng Class<?>. getName(): Trả về tên đầy đủ (fully qualified name) của kiểu dữ liệu đó dưới dạng chuỗi (String).
                            where.append(" AND b." + fieldName + " = " + value + " ");
                        }
                        else if(item.getType().getName().equals("java.lang.String")) {
                            where.append(" AND b." + fieldName + " LIKE '%" + value + "%' ");
                        }
                    }
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void specialQuery(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
        Long staffid = buildingSearchBuilder.getStaffId();
        if (staffid != null) {
            where.append(" AND a.staffid = " + staffid);
        }

        Long minArea = buildingSearchBuilder.getMinArea();
        Long maxArea = buildingSearchBuilder.getMaxArea();

        if (minArea != null || maxArea != null) {
            where.append(" AND EXISTS ( SELECT * FROM rentarea r WHERE b.id = r.buildingid ");
            if (minArea != null) {
                where.append(" AND r.value >= " + minArea);
            }
            if (maxArea != null) {
                where.append(" AND r.value <= " + maxArea);
            }
            where.append(") "); 												// code này đã dọn bỏ mấy cái comment rồi để nhìn cho đỡ rối
        }																		// lướt xuống cuối có bản chi tiết các cách làm khác nhau
        // có giải thích chi tiết ở dưới
        Long minPrice = buildingSearchBuilder.getMinPrice();
        Long maxPrice = buildingSearchBuilder.getMaxPrice();
        if (minPrice != null) {
            where.append(" AND b.rentprice >= " + minPrice);
        }
        if (maxPrice != null) {
            where.append(" AND b.rentprice <= " + maxPrice);
        }

        List <String> typecode = buildingSearchBuilder.getTypeCode();
        if (typecode != null && typecode.size() > 0)
        {
            where.append(" AND ( ");
            String s = typecode.stream().map(i -> " b.type LIKE '%" + i + "%' ").collect(Collectors.joining(" or "));
            where.append(s + " ) ");
        }
    }


    public static void groupByQuery(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where)
    {
        where.append(" group by b.id ");
        if(buildingSearchBuilder.getStaffId() != null) where.append(",a.id; ");
    }

    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {

        StringBuilder sql = new StringBuilder(
                "SELECT b.* from building b "); // thêm b.*, không được select * from,  vì với hibernate 6.x, Khi có hai cột có cùng tên (như id từ cả hai bảng), nó sẽ không thể phân biệt được và gây ra lỗi NonUniqueDiscoveredSqlAliasException. select * from nó lấy cả 2 cáo cột id của cả 2 bảng join với nhau
        joinTable(buildingSearchBuilder, sql);
        StringBuilder where = new StringBuilder(" WHERE 1 = 1");
        normalQuery(buildingSearchBuilder, where);
        specialQuery(buildingSearchBuilder, where);
        groupByQuery(buildingSearchBuilder, where);

        sql.append(where);
        System.out.println(sql.toString());
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();

    }
}
