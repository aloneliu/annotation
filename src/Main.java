import com.jinliu.annotation.Column;
import com.jinliu.annotation.Department;
import com.jinliu.annotation.Filter;
import com.jinliu.annotation.Table;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {
        Filter f1 = new Filter();
        f1.setId(10);

        Filter f2 = new Filter();
        f2.setId(1);
        f2.setName("Andy");
        f2.setFullName("Lucy");

        Filter f3 = new Filter();
        f3.setEmail("aa@aa.com");

        Department department = new Department();
        department.setName("技术部");
        department.setAmount(10);

        System.out.println(query(f1));
        System.out.println(query(f2));
        System.out.println(query(f3));

        System.out.println(query(department));
    }

    public static String query(Object object) {
        StringBuilder stringBuilder = new StringBuilder();

        // 1.获取class
        Class c = object.getClass();

        // 2.获取table name
        boolean exists = c.isAnnotationPresent(Table.class);
        if (!exists) {
            return null;
        }
        Table t = (Table) c.getAnnotation(Table.class);
        String tableName = t.value();

        stringBuilder.append("select * from ").append(tableName).append(" where 1=1");

        // 3.遍历所有的字段
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            // 判断字段是否包含注解
            boolean fExists = field.isAnnotationPresent(Column.class);
            if (!fExists) {
                continue;
            }

            // 字段的名称
            Column column = field.getAnnotation(Column.class);
            String columnValue = column.value();

            // 反射拿到字段的值
            Object fieldValue = null;
            String fieldName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
            try {
                Method method = c.getMethod(fieldName);
                fieldValue = method.invoke(object);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (fieldValue == null || (fieldValue instanceof Integer && (Integer) fieldValue == 0)) {
                continue;
            }
            stringBuilder.append(" and " + columnValue);
            if (fieldValue instanceof String) {
                if (((String) fieldValue).contains(",")) {
                    stringBuilder.append(" in(");
                    String[] split = ((String) fieldValue).split(",");
                    for (String s : split) {
                        stringBuilder.append("'" + s + "',");
                    }
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    stringBuilder.append(")");
                } else {
                    stringBuilder.append("=" + "'" + fieldValue + "'");
                }
            } else if (fieldValue instanceof Integer) {
                stringBuilder.append("=" + fieldValue);
            }

        }


        return stringBuilder.toString();
    }

}
