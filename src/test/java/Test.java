import com.zyz.demo.sharding.util.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Test {
//    private static String toUpperFirstChar(String s) {
//        char[] charArray = s.toCharArray();
//        charArray[0] -= 32;
//        return String.valueOf(charArray);
//    }

    public static void main(String[] args) throws SQLException {
        Connection connection = DBUtil.getConnection();
        Statement statement = connection.createStatement();
        statement.execute("show tables like 'article_%'");
//        statement.execute("show tables");
//        statement.execute("select * from user");
        ResultSet resultSet = statement.getResultSet();
        while (resultSet.next()) {
            System.out.println(resultSet.getString("Tables_in_ssmdemo (article_%)"));
        }

//        Scanner in = new Scanner(System.in);
//        char[] chars = new char[50];
//        int i = 0;
//        while (in.hasNext()) {
//            chars[i] = (char) in.nextShort();
//            i++;
//        }
//        System.out.println(chars);
        // 输入
        // 98 97 99 104 101 108 111 114 115 32 115 104 111 117 108 100 32 108 111 111 107 32 102 111 114 32 97 32 109 97 116 101

//        HashMap<String, String> map = new HashMap<>();
//        map.put("1", "3");
//        map.put("1", "4");
//        System.out.println(map);


//        char[] chars = new char[5];
//        chars[0] = 'i';
//        chars[1] = 'i';
//        System.out.println(String.valueOf(chars).trim());


//        String packageName = "com.zyz.demo.sharding.model";
//        String tableName = "user";
//        String entityName = packageName + "." + toUpperFirstChar(tableName);
//        try {
//            Class<?> entity = Class.forName(entityName);
//            System.out.println(entity);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

//        String sql = "select uid, name, pwd from user where name = ?";
//        int from = sql.indexOf("from");
//        int where = sql.indexOf("where");
//        System.out.println(sql.substring(from+5, where-1));
    }
}
