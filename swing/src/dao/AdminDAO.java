package dao;

import pojo.User;
import util.CommonUtil;
import util.DBUtils;

import java.util.ArrayList;
import java.util.List;

public class AdminDAO {
    public static final Object[] columnNames = {"ID", "账号", "密码"};
//    public static final List<Object[]> data = new ArrayList<>();
//    private static int maxId = 10;
//    //初始数据
//    static {
//        data.add(new Object[]{1,"admin","666666"});
//    }

    //根据账号查询
    public static User getUser(String username) {
        String sql = "select id, username, password from uadmin where username=?";
        List param = new ArrayList();
        param.add(username);
        Object[] obj = DBUtils.queryObject(sql, param, 3);
        if(obj == null){
            return null;
        }
        User user = new User();
        user.setId((int)obj[0]);
        user.setUsername((String)obj[1]);
        user.setPassword((String)obj[2]);
        user.setType(1);
        return user;
    }

    //修改密码
    public static void updatePassword(Integer id, String password) {
        String sql = "update uadmin set password=? where id=?";
        List param = new ArrayList();
        param.add(password);
        param.add(id);
        DBUtils.executeUpdate(sql, param);
    }

    //单条件查询
    public static Object[][] search(int col, String text){
        String sql = "select id, username, password from uadmin";
        List param = new ArrayList();
        if(col == 1){
            sql += " where username like concat('%',?,'%')";
            param.add(text);
        }
        return CommonUtil.toArray(DBUtils.queryList(sql, param, 3));
    }

    //添加数据
    public static void add(Object[] obj){
        String sql = "insert into uadmin(username, password) values(?,?)";
        List param = new ArrayList();
        for (int i = 1; i < obj.length; i++){
            param.add(obj[i]);
        }
        DBUtils.executeUpdate(sql, param);
    }

    //删除数据
    public static void remove(Object id){
        String sql = "delete from uadmin where id=?";
        List param = new ArrayList();
        param.add(id);
        DBUtils.executeUpdate(sql, param);
    }

    //根据主键查询
    public static Object[] findById(Object id){
        String sql = "select id, username, password from uadmin where id=?";
        List param = new ArrayList();
        param.add(id);
        return DBUtils.queryObject(sql, param, 3);
    }
}
