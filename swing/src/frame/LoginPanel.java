package frame;

import dao.AdminDAO;
import dao.CounselorDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import frame.admin.AdminPanel;
import frame.counselor.CounselorPanel;
import frame.student.StudentPanel;
import frame.teacher.TeacherPanel;
import pojo.User;
import util.SystemConstants;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class LoginPanel extends JPanel {
    private static final String dir = LoginPanel.class.getClassLoader().getResource("images").getPath();

    public LoginPanel(){
        this.setBounds(0,0, SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);
        this.setLayout(null);

        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                Graphics2D g2 = (Graphics2D) g;
                g2.drawImage(new ImageIcon(dir + "/arg.png").getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
            }
        };
        panel.setBounds(400,250,500,300);
        this.add(panel);

        Box box = Box.createVerticalBox();
        panel.add(box);
        box.add(Box.createVerticalStrut(15));

        Box box0 = Box.createHorizontalBox();
        JLabel title = new JLabel("学生请假管理系统");
        title.setFont(new Font("微软雅黑", Font.BOLD, 30));
        box0.add(title);
        box.add(box0);
        box.add(Box.createVerticalStrut(20));

        Font font = new Font("微软雅黑",Font.BOLD,20);
        Border border = BorderFactory.createLoweredBevelBorder();

        Box box1 = Box.createHorizontalBox();
        JLabel nameLabel = new JLabel("账 号：");
        nameLabel.setFont(font);
        box1.add(nameLabel);
        JTextField nameField = new JTextField(15);
        nameField.setBorder(border);
        box1.add(nameField);
        box.add(box1);

        box.add(Box.createVerticalStrut(15));
        Box box2 = Box.createHorizontalBox();
        JLabel pwdLabel = new JLabel("密 码：");
        pwdLabel.setFont(font);
        box2.add(pwdLabel);
        JPasswordField pwdField = new JPasswordField(15);
        pwdField.setBorder(border);
        box2.add(pwdField);
        box.add(box2);

        box.add(Box.createVerticalStrut(15));

        JRadioButton adminRadio = new JRadioButton("管理员",true);
        JRadioButton teacherRadio = new JRadioButton("教师");
        JRadioButton studentRadio = new JRadioButton("学生");
        adminRadio.setFont(font);
        adminRadio.setOpaque(false);
        adminRadio.setFocusable(false);
        teacherRadio.setFont(font);
        teacherRadio.setOpaque(false);
        teacherRadio.setFocusable(false);
        studentRadio.setFont(font);
        studentRadio.setOpaque(false);
        studentRadio.setFocusable(false);
        ButtonGroup group = new ButtonGroup();
        group.add(adminRadio);
        group.add(teacherRadio);
        group.add(studentRadio);
        Box box3 = Box.createHorizontalBox();
        box3.add(adminRadio);
        box3.add(teacherRadio);
        box3.add(studentRadio);
        box.add(box3);
        box.add(Box.createVerticalStrut(15));

        JButton loginBtn = new JButton("登录");
        loginBtn.setFont(font);

        Box box4 = Box.createHorizontalBox();
        box4.add(loginBtn);
        box.add(box4);
        loginBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String username = nameField.getText();
                String password = new String(pwdField.getPassword());

                User user = null;
                JPanel panel = null;
                if(adminRadio.isSelected()){
                    user = AdminDAO.getUser(username);
                    panel = new AdminPanel();
                }else if(teacherRadio.isSelected()){
                    user = TeacherDAO.getUser(username);
                    if(user == null){
                        user = CounselorDAO.getUser(username);
                        panel = new CounselorPanel();
                    }else{
                        panel = new TeacherPanel();
                    }
                }else if(studentRadio.isSelected()){
                    user = StudentDAO.getUser(username);
                    panel = new StudentPanel();
                }
                if(user == null || !user.getPassword().equals(password)){
                    JOptionPane.showMessageDialog(loginBtn.getParent(),"用户名或密码错误","系统提示",JOptionPane.WARNING_MESSAGE);
                }else{
                    MainFrame.setContent(panel);
                    MainFrame.user = user;
                }
            }
        });
    }

    //背景
    @Override
    protected void paintComponent(Graphics g){
        String dir = this.getClass().getClassLoader().getResource("images").getPath();
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(new ImageIcon(dir + "/bg.png").getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
    }
}
