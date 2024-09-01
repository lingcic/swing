package frame.admin;

import dao.DataDAO;
import frame.teacher.TeacherDataTablePanel;
import frame.teacher.TeacherPanel;
import util.SystemVerifier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

public class DataEditPanel extends JInternalFrame {
    public DataEditPanel(Object id) {
        super("编辑数据", true, true, true, true);
        this.setVisible(true);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        this.add(panel);

        Box box = Box.createVerticalBox();
        panel.add(box);
        box.add(Box.createVerticalStrut(55));

        Box box1 = Box.createHorizontalBox();
        box1.add(new JLabel("学    号："));
        JTextField field1 = new JTextField(10);
        box1.add(field1);
        box.add(box1);
        box.add(Box.createVerticalStrut(5));

        Box box2 = Box.createHorizontalBox();
        box2.add(new JLabel("姓    名："));
        JTextField field2 = new JTextField(10);
        box2.add(field2);
        box.add(box2);
        box.add(Box.createVerticalStrut(5));

        Box box3 = Box.createHorizontalBox();
        box3.add(new JLabel("性    别："));
        box3.add(Box.createHorizontalStrut(40));
        JRadioButton maleRadio = new JRadioButton("男", true);
        JRadioButton femaleRadio = new JRadioButton("女");
        box3.add(maleRadio);
        box3.add(femaleRadio);
        box3.add(Box.createHorizontalStrut(30));
        box.add(box3);

        maleRadio.setOpaque(false);
        maleRadio.setFocusPainted(false);
        femaleRadio.setOpaque(false);
        femaleRadio.setFocusPainted(false);

        ButtonGroup group = new ButtonGroup();
        group.add(maleRadio);
        group.add(femaleRadio);
        box.add(Box.createVerticalStrut(15));

        Box box4 = Box.createHorizontalBox();
        box4.add(new JLabel("班    级："));
        JTextField field4 = new JTextField(10);
        box4.add(field4);
        box.add(box4);
        box.add(Box.createVerticalStrut(5));

        Box box5 = Box.createHorizontalBox();
        box5.add(new JLabel("学    院："));
        JTextField field5 = new JTextField(10);
        box5.add(field5);
        box.add(box5);
        box.add(Box.createVerticalStrut(5));

        Box box6 = Box.createHorizontalBox();
        box6.add(new JLabel("电    话："));
        JTextField field6 = new JTextField(10);
        box6.add(field6);
        box.add(box6);
        box.add(Box.createVerticalStrut(5));

        Box box7 = Box.createHorizontalBox();
        box7.add(new JLabel("开始日期："));
        JTextField field7 = new JTextField(10);
        box7.add(field7);
        box.add(box7);
        box.add(Box.createVerticalStrut(5));

        Box box8 = Box.createHorizontalBox();
        box8.add(new JLabel("结束日期："));
        JTextField field8 = new JTextField(10);
        box8.add(field8);
        box.add(box8);
        box.add(Box.createVerticalStrut(5));

        Box box9 = Box.createHorizontalBox();
        box9.add(new JLabel("请假天数："));
        JTextField field9 = new JTextField(10);
        box9.add(field9);
        box.add(box9);
        box.add(Box.createVerticalStrut(5));

        Box box10 = Box.createHorizontalBox();
        box10.add(new JLabel("请假课时："));
        JTextField field10 = new JTextField(10);
        box10.add(field10);
        box.add(box10);
        box.add(Box.createVerticalStrut(5));

        Box box11 = Box.createHorizontalBox();
        box11.add(new JLabel("请假原因："));
        JTextField field11 = new JTextField(10);
        field11.setInputVerifier(SystemVerifier.emptyVerify("请假原因", 2, null));
        box11.add(field11);
        box.add(box11);
        box.add(Box.createVerticalStrut(5));

        Box box12 = Box.createHorizontalBox();
        box12.add(new JLabel("班导审批："));
        box12.add(Box.createHorizontalStrut(20));
        JRadioButton YRadio = new JRadioButton("通过");
        JRadioButton NRadio = new JRadioButton("拒绝");
        box12.add(YRadio);
        box12.add(NRadio);
        box12.add(Box.createHorizontalStrut(20));
        box.add(box12);

        YRadio.setOpaque(false);
        YRadio.setFocusPainted(false);
        NRadio.setOpaque(false);
        NRadio.setFocusPainted(false);

        ButtonGroup group2 = new ButtonGroup();
        group2.add(YRadio);
        group2.add(NRadio);
        box.add(Box.createVerticalStrut(15));

        Box box13 = Box.createHorizontalBox();
        box13.add(new JLabel("班导意见："));
        JTextField field13 = new JTextField(10);
        field13.setInputVerifier(SystemVerifier.emptyVerify("意见", null, null));
        box13.add(field13);
        box.add(box13);
        box.add(Box.createVerticalStrut(5));

        Box box14 = Box.createHorizontalBox();
        box14.add(new JLabel("辅导员审批："));
        box14.add(Box.createHorizontalStrut(20));
        JRadioButton Y1Radio = new JRadioButton("通过");
        JRadioButton N1Radio = new JRadioButton("拒绝");
        box14.add(YRadio);
        box14.add(NRadio);
        box14.add(Box.createHorizontalStrut(20));
        box.add(box14);

        Y1Radio.setOpaque(false);
        Y1Radio.setFocusPainted(false);
        N1Radio.setOpaque(false);
        N1Radio.setFocusPainted(false);

        ButtonGroup group3 = new ButtonGroup();
        group3.add(Y1Radio);
        group3.add(N1Radio);
        box.add(Box.createVerticalStrut(15));

        Box box15 = Box.createHorizontalBox();
        box15.add(new JLabel("辅导员意见："));
        JTextField field15 = new JTextField(10);
        field15.setInputVerifier(SystemVerifier.emptyVerify("意见", null, null));
        box15.add(field15);
        box.add(box15);
        box.add(Box.createVerticalStrut(5));

        //修改
        if(id != null) {
            Object[] data = DataDAO.findById(id);
            field1.setText((String) data[1]);
            field1.setEditable(false);
            field2.setText((String) data[2]);
            field2.setEditable(false);
            if ("男".equals(data[3])) {
                maleRadio.setSelected(true);
            } else {
                femaleRadio.setSelected(true);
            }
            field4.setText((String) data[4]);
            field5.setText((String) data[5]);
            field6.setText((String) data[6]);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            String tmp7 = sdf.format(data[7]);
            field7.setText(tmp7);
            String tmp8 = sdf.format(data[8]);
            field8.setText(tmp8);
            field9.setText(String.valueOf(data[9]));
            field10.setText(String.valueOf(data[10]));
            field11.setText((String) data[11]);
            if ("通过".equals(data[14])) {
                YRadio.setSelected(true);
            } else {
                NRadio.setSelected(true);
            }
            field13.setText((String) data[15]);
            if ("通过".equals(data[18])) {
                Y1Radio.setSelected(true);
            } else {
                N1Radio.setSelected(true);
            }
            field15.setText((String) data[19]);

        }

        JButton btn = new JButton("确认");
        Box boxBtn = Box.createHorizontalBox();
        boxBtn.add(Box.createHorizontalStrut(40));
        boxBtn.add(btn);
        box.add(boxBtn);

        btn.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                if(!YRadio.isSelected() && !NRadio.isSelected()){
                    JOptionPane.showMessageDialog(null,"未审批", "系统提示", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                Object[] user = DataDAO.findById(id);
                Object[] data = new Object[]{id, YRadio.isSelected() ? "通过" : "拒绝", field13.getText(), user[18], user[19]};
                DataDAO.update(id, data);
                AdminPanel.setContent(new DataTablePanel());
            }
        });
    }
}
