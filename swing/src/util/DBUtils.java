package util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
    private static final String url = "jdbc:sqlserver://LAPTOP-IDDV49D0:1433;database=student;encrypt=false";
    private static final String username = "qiu";
    private static final String password = "mysql";

    static {
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("数据库加载成功");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    public static Connection open(){
        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static PreparedStatement preparedStatement(String sql, List param, Connection connection){
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            if(param != null){
                for(int i = 0; i < param.size(); i++){
                    statement.setObject(i + 1, param.get(i));
                }
            }
            return statement;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static void closeAll(Connection connection, PreparedStatement statement, ResultSet resultSet){
        try {
            if(resultSet != null)
                resultSet.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            if(statement != null)
                statement.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        try {
            if(connection != null)
                connection.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static int executeUpdate(String sql, List param){
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = open();
            statement = preparedStatement(sql, param, connection);
            return statement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeAll(connection, statement, null);
        }
        return 0;
    }

    public static int executeInsert(String sql, List param){
        PreparedStatement statement = null;
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = open();
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            if(param != null){
                for(int i = 0; i < param.size(); i++){
                    statement.setObject(i + 1, param.get(i));
                }
            }
            statement.executeUpdate();
            rs = statement.getGeneratedKeys();
            if(rs.next()){
                return rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeAll(connection, statement, null);
        }
        return 0;
    }

    public static Object[] queryObject(String sql, List param, int col){
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = open();
            ps = preparedStatement(sql, param, connection);
            rs = ps.executeQuery();
            if(rs.next()){
                Object[] obj = new Object[col];
                for(int i = 0; i < col; i++){
                    obj[i] = rs.getObject(i + 1);
                }
                return obj;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeAll(connection, ps, rs);
        }
        return null;
    }

    public static List<Object[]> queryList(String sql, List param, int col){
        List<Object[]> result = new ArrayList<>();
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = open();
            ps = preparedStatement(sql, param, connection);
            rs = ps.executeQuery();
            while(rs.next()){
                Object[] obj = new Object[col];
                for(int i = 0; i < col; i++){
                    obj[i] = rs.getObject(i + 1);
                }
                result.add(obj);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            closeAll(connection, ps, rs);
        }
        return result;
    }
}
