package dao;

import pojo.User;
import util.CommonUtil;
import util.DBUtils;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public static final Object[] columnNames = {"ID", "学号", "密码", "姓名", "性别", "班级", "学院", "电话", "班导工号", "辅导员工号"};
    public static final Object[] columnNames1 = {"ID", "学号", "密码", "姓名", "性别", "班级", "学院", "电话", "班导", "辅导员"};

//    public static final List<Object[]> data = new ArrayList<>();
//    private static int maxId = 10;
//
//    //初始数据
//    static {
//        data.add(new Object[]{1,"3220421001","000000","小秋","女", "计算机221班", "数据学院", "200001", "10001", "10004"});
//        data.add(new Object[]{2,"3220421002","000000","小金","女", "计算机221班", "数据学院", "200002", "10001", "10004"});
//    }

    //根据学号查询
    public static User getUser(String username) {
        String sql = "select sid, susername, spassword from ustudent where susername = ?";
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
        user.setType(3);
        return user;
    }

    //修改密码
    public static void updatePassword(Integer id, String password) {
        String sql = "update ustudent set spassword=? where sid=?";
        List param = new ArrayList();
        param.add(password);
        param.add(id);
        DBUtils.executeUpdate(sql, param);
    }

    //单条件查询
    public static Object[][] search(int col, String text){
        String sql = "SELECT s.sid, s.susername, s.spassword, s.sname, s.ssexy, g.gname, d.dname, s.stele, t.tname, c.cname " +
                "FROM ustudent s " +
                "JOIN ugrade g ON s.gid = g.gid " +
                "JOIN udept d ON g.did = d.did " +
                "JOIN uteacher t ON g.tid = t.tid " +
                "JOIN ucounselor c ON c.did = d.did ";
        List param = new ArrayList();
        if(col == 1 && text.length() > 0){
            sql += "WHERE s.susername = ?;";
            param.add(text);
        }
        return CommonUtil.toArray(DBUtils.queryList(sql, param, 10));
    }

    //添加数据
    public static void add(Object[] obj){
        String sql = "insert into ustudent(susername, spassword, sname, ssexy, gid, stele) " +
                "values(?,?,?,?,?,?)";
        List param = new ArrayList();
        for (int i = 1; i < obj.length; i++){
            param.add(obj[i]);
        }
        DBUtils.executeUpdate(sql, param);
    }

    //修改数据
    public static void update(Object id, Object[] obj){
        String sql = "update ustudent set sname=?, ssexy=?, gid=?, stele=? where sid=?";
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
        String sql = "delete from ustudent where sid=?";
        List param = new ArrayList();
        param.add(id);
        DBUtils.executeUpdate(sql, param);
    }

    //根据主键查询
    public static Object[] findById(Object id){
        String sql = "SELECT s.sid, s.susername, s.spassword, s.sname, s.ssexy, g.gname, d.dname, s.stele, t.tid, c.cid " +
                "FROM ustudent s " +
                "JOIN ugrade g ON s.gid = g.gid " +
                "JOIN udept d ON g.did = d.did " +
                "JOIN uteacher t ON g.tid = t.tid " +
                "JOIN ucounselor c ON c.did = d.did " +
                "WHERE sid=?;";
        List param = new ArrayList();
        param.add(id);
        return DBUtils.queryObject(sql, param, 10);
    }
}
