package frame.admin;

import dao.TeacherDAO;
import frame.MainFrame;
import util.SystemVerifier;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TeacherEditPanel extends JInternalFrame {
    public TeacherEditPanel(Object id) {
        super("教师信息", true, true, true, true);
        this.setVisible(true);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        this.add(panel);

        Box box = Box.createVerticalBox();
        panel.add(box);
        box.add(Box.createVerticalStrut(55));

        Box box1 = Box.createHorizontalBox();
        box1.add(new JLabel("工    号："));
        JTextField field1 = new JTextField(10);
        field1.setInputVerifier(SystemVerifier.emptyVerify("工号", 2, null));
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
        box4.add(new JLabel("院    系："));
        JTextField field3 = new JTextField(10);
        field3.setInputVerifier(SystemVerifier.emptyVerify("院系", 2, null));
        box4.add(field3);
        box.add(box4);
        box.add(Box.createVerticalStrut(5));

        Box box5 = Box.createHorizontalBox();
        box5.add(new JLabel("电    话："));
        JTextField field4 = new JTextField(10);
        field4.setInputVerifier(SystemVerifier.emptyVerify("电话", 2, null));
        box5.add(field4);
        box.add(box5);
        box.add(Box.createVerticalStrut(5));

        JButton btn = new JButton("提交");
        Box boxBtn = Box.createHorizontalBox();
        boxBtn.add(Box.createHorizontalStrut(40));
        boxBtn.add(btn);
        box.add(boxBtn);

        //修改
        if(id != null){
            Object[] user = TeacherDAO.findById(id);
            field1.setText((String)user[1]);
            field1.setEditable(false);
            field2.setText((String)user[3]);
            field3.setText((String)user[5]);
            field4.setText((String)user[6]);
            if("男".equals(user[4])){
                maleRadio.setSelected(true);
            }else{
                femaleRadio.setSelected(true);
            }
        }


        btn.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                if(!field3.getInputVerifier().verify(field3)){
                    return;
                }
                Object[] data = new Object[]{id, field1.getText(), "000000", field2.getText(), maleRadio.isSelected() ? "男" : "女", field3.getText(), field4.getText()};
                if(id == null){
                    if(TeacherDAO.getUser(field1.getText()) != null){
                        JOptionPane.showMessageDialog(btn.getParent(),"工号已存在","系统提示",JOptionPane.WARNING_MESSAGE);
                        return;
                    }
                    TeacherDAO.add(data);
                }else {
                    TeacherDAO.update(id, data);
                }
                AdminPanel.setContent(new TeacherTablePanel());
            }
        });
        }
    }
