package frame.student;

import dao.DataDAO;
import dao.StudentDAO;
import frame.MainFrame;
import frame.admin.AdminPanel;
import util.CommonUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StudentDataTablePanel extends JInternalFrame {
    private JTable table = new JTable(){
      public boolean isCellEditable(int row, int column){
          return false;
      }
    };

    public StudentDataTablePanel() {
        super("请假单列表", true, true, true, true);
        this.setVisible(true);

        JPanel panel = new JPanel(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);
        panel.add(table, BorderLayout.CENTER);
        panel.add(table.getTableHeader(), BorderLayout.NORTH);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        search();

    }
    //查询方法
    private void search(){
        Object[] user = StudentDAO.findById(MainFrame.user.getId());
        TableModel tableModel = new DefaultTableModel(DataDAO.search(1, user[1].toString()), DataDAO.columnNames);
//        TableModel tableModel = new DefaultTableModel(DataDAO.searchMulti(new int[]{1, 2}, new String[]{field1.getText(), field2.getText()}), DataDAO.columnNames);
        table.setModel(tableModel);

        //隐藏列
        CommonUtil.hide(table, 1, 2, 3, 4, 5, 6, 12, 16);
    }
}
