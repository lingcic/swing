package dao;

import util.CommonUtil;
import util.DBUtils;

import java.util.ArrayList;
import java.util.List;

public class DataDAO {
    public static final Object[] columnNames = {"请假单号", "学号", "姓名", "性别", "班级", "学院", "电话", "开始日期", "结束日期", "请假天数", "请假课时数", "请假原因", "班导工号", "班导姓名", "班导审批", "班导意见", "辅导员工号", "辅导员姓名", "辅导员审批", "辅导员意见"};

    //单条件查询
    public static Object[][] search(int col, String text){
        String sql = "SELECT l.lid, s.susername, s.sname, s.ssexy, g.gname, d.dname, s.stele, l.lstart, l.lend, l.lday, l.lnum, l.lreason, t.tusername, t.tname, l.lteacher, l.ltidea, c.cusername, c.cname, l.lcounselor, l.lcidea " +
                "FROM uleave l " +
                "JOIN ustudent s ON s.sid = l.sid " +
                "JOIN ugrade g ON g.gid = s.gid " +
                "JOIN udept d ON d.did = g.did " +
                "JOIN uteacher t ON t.tid = l.ltid " +
                "JOIN ucounselor c ON c.cid = l.lcid ";
        List param = new ArrayList();
        if(col == 1 && text.length() > 0){
            sql += "WHERE s.susername =? ;";
            param.add(text);
        }
        return CommonUtil.toArray(DBUtils.queryList(sql, param, 20));
    }

    //多条件查询
    public static Object[][] searchMulti(int[] cols, String[] texts){
        String sql = "SELECT l.lid, s.susername, s.sname, s.ssexy, g.gname, d.dname, s.stele, l.lstart, l.lend, l.lday, l.lnum, l.lreason, t.tusername, t.tname, l.lteacher, l.ltidea, c.cusername, c.cname, l.lcounselor, l.lcidea " +
                "FROM uleave l " +
                "JOIN ustudent s ON s.sid = l.sid " +
                "JOIN ugrade g ON g.gid = s.gid " +
                "JOIN udept d ON d.did = g.did " +
                "JOIN uteacher t ON t.tid = l.ltid " +
                "JOIN ucounselor c ON c.cid = l.lcid " +
                "WHERE 1 = 1 ";
        List param = new ArrayList();
        if(cols != null && cols.length > 0){
            for(int i = 0; i < cols.length; i++){
                if(CommonUtil.isEmpty(texts[i]))
                    continue;
                switch (cols[i]){
                    case 1:
                        sql += "AND s.susername =? ";
                        break;
                    case 12:
                        sql += "AND t.tusername =? ";
                        break;
                    case 16:
                        sql += "AND c.cusername =? ";
                        break;
                    case 14:
                        sql += "AND convert(nvarchar(255),l.lteacher) =? ";
                        break;
                    case 18:
                        sql += "AND convert(nvarchar(255),l.lcounselor) =? ";
                        break;
                }
                param.add(texts[i]);
            }
        }
        return CommonUtil.toArray(DBUtils.queryList(sql, param, 20));
    }

    //添加数据
    public static void add(Object[] obj){
        String sql = "insert into uleave(sid, lstart, lend, lday, lnum, lreason, ltid, lteacher, ltidea, lcid, lcounselor, lcidea ) " +
                "values(?,?,?,?,?,?,?,?,?,?,?,?)";
        List param = new ArrayList();
        for (int i = 1; i < obj.length; i++){
            param.add(obj[i]);
        }
        DBUtils.executeUpdate(sql, param);
    }

    //根据主键查询
    public static Object[] findById(Object id){
        String sql = "SELECT l.lid, s.susername, s.sname, s.ssexy, g.gname, d.dname, s.stele, l.lstart, l.lend, l.lday, l.lnum, l.lreason, t.tusername, t.tname, l.lteacher, l.ltidea, c.cusername, c.cname, l.lcounselor, l.lcidea " +
                "FROM uleave l " +
                "JOIN ustudent s ON s.sid = l.sid " +
                "JOIN ugrade g ON g.gid = s.gid " +
                "JOIN udept d ON d.did = g.did " +
                "JOIN uteacher t ON t.tid = l.ltid " +
                "JOIN ucounselor c ON c.cid = l.lcid " +
                "WHERE l.lid = ?";
        List param = new ArrayList();
        param.add(id);
        return DBUtils.queryObject(sql, param, 20);
    }

    //修改数据
    public static void update(Object id, Object[] obj){
        String sql = "UPDATE uleave " +
                "SET lteacher=?, ltidea=?, lcounselor=?, lcidea=? " +
                "WHERE lid=?";
        List param = new ArrayList();
        param.add(obj[1]);
        param.add(obj[2]);
        param.add(obj[3]);
        param.add(obj[4]);
        param.add(id);
        DBUtils.executeUpdate(sql, param);
    }

    //删除数据
    public static void remove(Object id){
        String sql = "delete from uleave where lid=?";
        List param = new ArrayList();
        param.add(id);
        DBUtils.executeUpdate(sql, param);
    }
}
