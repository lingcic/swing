package dao;

import pojo.User;
import util.CommonUtil;
import util.DBUtils;

import java.util.ArrayList;
import java.util.List;

public class CounselorDAO {
    public static final Object[] columnNames = {"ID", "工号", "密码", "姓名", "性别", "院系", "电话"};
//    public static final List<Object[]> data = new ArrayList<>();
//    private static int maxId = 10;
//
//    //初始数据
//    static {
//        data.add(new Object[]{1, "10002","000000", "李老师", "男", "数据学院", "100002"});
//    }

    //根据账号查询
    public static User getUser(String username) {
        String sql = "select cid, cusername, cpassword from ucounselor where cusername = ?";
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
        user.setType(4);
        return user;
    }

    //修改密码
    public static void updatePassword(Integer id, String password) {
        String sql = "update ucounselor set cpassword=? where cid=?";
        List param = new ArrayList();
        param.add(password);
        param.add(id);
        DBUtils.executeUpdate(sql, param);
    }

    //单条件查询
    public static Object[][] search(int col, String text){
        String sql = "SELECT c.cid, c.cusername, c.cpassword, c.cname, c.csexy, d.dname, c.ctele " +
                "FROM ucounselor c " +
                "JOIN udept d ON c.did = d.did ";
        List param = new ArrayList();
        if(col == 1 && text.length() > 0){
            sql += "WHERE c.cusername = ?;";
            param.add(text);
        }
        return CommonUtil.toArray(DBUtils.queryList(sql, param, 7));
    }

    //添加数据
    public static void add(Object[] obj){
        String sql = "insert into ucounselor(cusername, cpassword, cname, csexy, did, ctele) " +
                "values(?,?,?,?,?,?)";
        List param = new ArrayList();
        for (int i = 1; i < obj.length; i++){
            param.add(obj[i]);
        }
        DBUtils.executeUpdate(sql, param);
    }

    //修改数据
    public static void update(Object id, Object[] obj){
        String sql = "update ucounselor set cname=?, csexy=?, did=?, ctele=? where cid=?";
        List param = new ArrayList();
        param.add(obj[3]);
        param.add(obj[4]);
        param.add(obj[5]);
        param.add(obj[6]);
        param.add(id);
        DBUtils.executeUpdate(sql, param);
    }

    //删除数据
    public static void remove(Object id){
        String sql = "delete from ucounselor where cid=?";
        List param = new ArrayList();
        param.add(id);
        DBUtils.executeUpdate(sql, param);
    }

    //根据主键查询
    public static Object[] findById(Object id){
        String sql = "select cid, cusername, cpassword, cname, csexy, dname, ctele " +
                "from udept, ucounselor " +
                "where ucounselor.did = udept.did " +
                "and cid=?";
        List param = new ArrayList();
        param.add(id);
        return DBUtils.queryObject(sql, param, 7);
    }
}
