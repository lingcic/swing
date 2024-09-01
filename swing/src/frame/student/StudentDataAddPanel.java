package frame.student;

import dao.DataDAO;
import dao.StudentDAO;
import frame.MainFrame;
import frame.PasswordPanel;
import util.SystemVerifier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StudentDataAddPanel extends JInternalFrame {
    public StudentDataAddPanel() {
        super("填写请假单", true, true, true, true);
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
        field1.setInputVerifier(SystemVerifier.emptyVerify("学号", 2, null));
        box1.add(field1);
        box.add(box1);
        box.add(Box.createVerticalStrut(5));

        Box box2 = Box.createHorizontalBox();
        box2.add(new JLabel("姓    名："));
        JTextField field2 = new JTextField(10);
        field2.setInputVerifier(SystemVerifier.emptyVerify("姓名", 2, null));
        box2.add(field2);
        box.add(box2);
        box.add(Box.createVerticalStrut(5));

        Box box3 = Box.createHorizontalBox();
        box3.add(new JLabel("性    别："));
        box3.add(Box.createHorizontalStrut(20));
        JRadioButton maleRadio = new JRadioButton("男", true);
        JRadioButton femaleRadio = new JRadioButton("女");
        box3.add(maleRadio);
        box3.add(femaleRadio);
        box3.add(Box.createHorizontalStrut(20));
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
        field4.setInputVerifier(SystemVerifier.emptyVerify("班级", 2, null));
        box4.add(field4);
        box.add(box4);
        box.add(Box.createVerticalStrut(5));

        Box box5 = Box.createHorizontalBox();
        box5.add(new JLabel("学    院："));
        JTextField field5 = new JTextField(10);
        field5.setInputVerifier(SystemVerifier.emptyVerify("学院", 2, null));
        box5.add(field5);
        box.add(box5);
        box.add(Box.createVerticalStrut(5));

        Box box6 = Box.createHorizontalBox();
        box6.add(new JLabel("电    话："));
        JTextField field6 = new JTextField(10);
        field6.setInputVerifier(SystemVerifier.emptyVerify("电话", 2, null));
        box6.add(field6);
        box.add(box6);
        box.add(Box.createVerticalStrut(5));

        Box box7 = Box.createHorizontalBox();
        box7.add(new JLabel("开始日期："));
        JSpinner spinner1 = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE));
        JSpinner.DateEditor editor1 = new JSpinner.DateEditor(spinner1, "yyyy-MM-dd HH:mm");
        spinner1.setEditor(editor1);
        box7.add(spinner1);
        box.add(box7);
        box.add(Box.createVerticalStrut(5));

        Box box8 = Box.createHorizontalBox();
        box8.add(new JLabel("结束日期："));
        JSpinner spinner2 = new JSpinner(new SpinnerDateModel(new Date(), null, null, Calendar.MINUTE));
        JSpinner.DateEditor editor2 = new JSpinner.DateEditor(spinner2, "yyyy-MM-dd HH:mm");
        spinner2.setEditor(editor2);
        box8.add(spinner2);
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
        field10.setInputVerifier(SystemVerifier.emptyVerify("请假课时数", null, null));
        box10.add(field10);
        box.add(box10);
        box.add(Box.createVerticalStrut(5));

        field10.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e) {
                Date date1 = (Date) spinner1.getValue();
                Date date2 = (Date) spinner2.getValue();
                if (date2.before(date1)) {
                    JOptionPane.showMessageDialog(null, "结束时间不得早于开始时间", "系统错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                double days = (double) (date2.getTime() - date1.getTime()) / (1000 * 60 * 60 * 24);
                field9.setText(String.format("%.1f", days));
            }
        });

        Box box11 = Box.createHorizontalBox();
        box11.add(new JLabel("请假原因："));
        JTextField field11 = new JTextField(10);
        field11.setInputVerifier(SystemVerifier.emptyVerify("请假原因", 2, null));
        box11.add(field11);
        box.add(box11);
        box.add(Box.createVerticalStrut(5));

        JButton btn = new JButton("提交");
        Box boxBtn = Box.createHorizontalBox();
        boxBtn.add(Box.createHorizontalStrut(40));
        boxBtn.add(btn);
        box.add(boxBtn);

        //修改
        Object[] user = StudentDAO.findById(MainFrame.user.getId());
        field1.setText((String)user[1]);
        field1.setEditable(false);
        field2.setText((String)user[3]);
        field2.setEditable(false);
        if("男".equals(user[4])){
            maleRadio.setSelected(true);
        }else{
            femaleRadio.setSelected(true);
        }
        maleRadio.setEnabled(false);
        femaleRadio.setEnabled(false);
        field4.setText((String)user[5]);
        field4.setEditable(false);
        field5.setText((String)user[6]);
        field5.setEditable(false);
        field6.setText((String)user[7]);
        field9.setEditable(false);

        btn.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println(user[0]);
                Object[] data = new Object[]{null, user[0], new SimpleDateFormat("yyyy-MM-dd HH:mm").format(spinner1.getValue()), new SimpleDateFormat("yyyy-MM-dd HH:mm").format(spinner2.getValue()), field9.getText(), field10.getText(), field11.getText(), user[8], "未审批", "", user[9], "未审批", ""};
                DataDAO.add(data);
                StudentPanel.setContent(new StudentDataTablePanel());
            }
        });
    }
}

