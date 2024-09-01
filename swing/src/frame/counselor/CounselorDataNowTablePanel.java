package frame.counselor;

import dao.CounselorDAO;
import dao.DataDAO;
import frame.MainFrame;
import util.CommonUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CounselorDataNowTablePanel extends JInternalFrame {
    private JTextField field1 = new JTextField(10);

    private JTable table = new JTable(){
      public boolean isCellEditable(int row, int column){
          return false;
      }
    };

    public CounselorDataNowTablePanel() {
        super("未审核请假单列表", true, true, true, true);
        this.setVisible(true);

        JPanel topPanel = new JPanel();
        this.add(topPanel, BorderLayout.NORTH);

        topPanel.add(new JLabel("学号："));
        topPanel.add(field1);
        JButton searchBtn = new JButton("查询");
        topPanel.add(searchBtn);
        JButton editBtn = new JButton("审批");
        topPanel.add(editBtn);

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

        editBtn.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                int rowNum = table.getSelectedRow();
                if(rowNum <= -1){
                    return;
                }
                CounselorPanel.setContent(new CounselorDataEditPanel(table.getValueAt(rowNum, 0)));
            }
        });
    }
    //查询方法
    private void search(){
        Object[] user = CounselorDAO.findById(MainFrame.user.getId());
        TableModel tableModel = null;
        tableModel = new DefaultTableModel(DataDAO.searchMulti(new int[]{16, 1, 14, 18}, new String[]{user[1].toString(), field1.getText(), "通过", "未审批"}), DataDAO.columnNames);
        table.setModel(tableModel);

        //隐藏列
        CommonUtil.hide(table, 12, 16);
    }
}
