package com.javaweb.repository.custom.impl;


import com.javaweb.builder.CustomerSearchBuilder;
import com.javaweb.entity.CustomerEntity;
import com.javaweb.repository.custom.CustomerRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Field;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepositoryCustom {


    @PersistenceContext
    private EntityManager entityManager;

    public void joinTable(CustomerSearchBuilder customerSearchBuilders, StringBuilder sql) {

        Long staffid = customerSearchBuilders.getStaffid();

        // kiểu Long chỉ cần check != null là được rồi
        if (staffid != null) {
            sql.append(" JOIN assignmentcustomer a ON c.id = a.customerid AND a.staffid = " + staffid);
        }
    }

    public void normalQuery(CustomerSearchBuilder customerSearchBuilders, StringBuilder where) {
        try {
            Field[] fields = CustomerSearchBuilder.class.getDeclaredFields();
            for(Field item : fields) {
                item.setAccessible(true); // phải có lệnh này thì nó mới cho lấy dữ liệu
                String fieldName = item.getName(); // trả về tên của thuộc tính như được định nghĩa trong class
                if (!fieldName.equals("staffid")) {
                    Object value = item.get(customerSearchBuilders); // Phương thức get() của Field trong Java Reflection luôn trả về giá trị dưới dạng Object, bất kể kiểu dữ liệu gốc của thuộc tính đó là kiểu gì., không nên dùng toString() ở dòng này vì nếu giá trị là null thì toString() sẽ bị NullPointerException
                    if( value != null ) {
                        where.append(" AND c." + fieldName + " LIKE '%" + value + "%' ");
                    }
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void groupByQuery(CustomerSearchBuilder customerSearchBuilders, StringBuilder where)
    {
        where.append(" group by c.id ");
    }

    @Override
    public List<CustomerEntity> findAll(CustomerSearchBuilder customerSearchBuilders) {

        StringBuilder sql = new StringBuilder("SELECT c.* FROM customer c" );
        joinTable(customerSearchBuilders, sql);
        StringBuilder where = new StringBuilder(" WHERE 1 = 1 and c.is_Active = 1");
        normalQuery(customerSearchBuilders, where);
        groupByQuery(customerSearchBuilders, where);
        sql.append(where);

        System.out.println(sql.toString());
        Query query = entityManager.createNativeQuery(sql.toString(), CustomerEntity.class);
        return query.getResultList();
    }



}
