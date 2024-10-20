package com.javaweb.repository.custom.impl;


import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.repository.BuildingRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
                if (!fieldName.equals("staffid") && !fieldName.equals("typecode")
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
            where.append(" AND assignmentbuilding.staffid = " + staffid);
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


    @Override
    public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder) {

        StringBuilder sql = new StringBuilder(
                "SELECT * from building b ");
        joinTable(buildingSearchBuilder, sql);
        StringBuilder where = new StringBuilder(" WHERE 1 = 1");
        normalQuery(buildingSearchBuilder, where);
        specialQuery(buildingSearchBuilder, where);
        sql.append(where).append(" GROUP BY b.id");
        System.out.println("hellomother"+sql.toString());
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
        return query.getResultList();

    }
}

