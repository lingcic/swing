package frame.admin;

import dao.DataDAO;
import dao.TeacherDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DataTablePanel extends JInternalFrame {
    private JTextField field1 = new JTextField(10);
//    private JTextField field2 = new JTextField(10);

    private JTable table = new JTable(){
      public boolean isCellEditable(int row, int column){
          return false;
      }
    };

    public DataTablePanel() {
        super("请假单列表", true, true, true, true);
        this.setVisible(true);

        JPanel topPanel = new JPanel();
        this.add(topPanel, BorderLayout.NORTH);

        topPanel.add(new JLabel("学号："));
        topPanel.add(field1);
//        topPanel.add(new JLabel("列2："));
//        topPanel.add(field2);
        JButton searchBtn = new JButton("查询");
        topPanel.add(searchBtn);
//        JButton addBtn = new JButton("添加");
//        topPanel.add(addBtn);
//        JButton editBtn = new JButton("编辑");
//        topPanel.add(editBtn);
//        JButton delBtn = new JButton("删除");
//        topPanel.add(delBtn);

        JPanel panel = new JPanel(new BorderLayout());
        this.add(panel, BorderLayout.CENTER);
        panel.add(table, BorderLayout.CENTER);
        panel.add(table.getTableHeader(), BorderLayout.NORTH);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        search();

        searchBtn.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                search();
            }
        });

//        addBtn.addMouseListener(new MouseAdapter(){
//            @Override
//            public void mousePressed(MouseEvent e) {
//                AdminPanel.setContent(new DataEditPanel(null));
//            }
//        });
//
//        editBtn.addMouseListener(new MouseAdapter(){
//            @Override
//            public void mousePressed(MouseEvent e) {
//                int rowNum = table.getSelectedRow();
//                if(rowNum <= -1){
//                    return;
//                }
//                AdminPanel.setContent(new DataEditPanel(table.getValueAt(rowNum, 0)));
//            }
//        });
//
//        delBtn.addMouseListener(new MouseAdapter(){
//            @Override
//            public void mousePressed(MouseEvent e) {
//                int rowNum = table.getSelectedRow();
//                if(rowNum <= -1){
//                    return;
//                }
//                DataDAO.remove((int)table.getValueAt(rowNum, 0));
//                search();
//            }
//        });
    }
    //查询方法
    private void search(){
        TableModel tableModel = new DefaultTableModel(DataDAO.search(1, field1.getText()), DataDAO.columnNames);
        table.setModel(tableModel);

        //隐藏列
//        CommonUtil.hide(table, 1, 2);
    }
}
