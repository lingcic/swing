package frame;

import dao.AdminDAO;
import dao.CounselorDAO;
import dao.StudentDAO;
import dao.TeacherDAO;
import util.SystemVerifier;

import javax.swing.*;
import java.awt.*;

public class PasswordPanel extends JInternalFrame {
    public PasswordPanel() {
        super("修改密码", true, true, true, true);
        this.setVisible(true);

        JPanel panel = new JPanel();
        panel.setBackground(Color.WHITE);
        this.add(panel);

        Box box = Box.createVerticalBox();
        panel.add(box);
        box.add(Box.createVerticalStrut(55));

        Box box1 = Box.createHorizontalBox();
        box1.add(new JLabel("原始密码："));
        JPasswordField pwdField = new JPasswordField(15);
        pwdField.setInputVerifier(SystemVerifier.emptyVerify("原始密码", 6, 20));
        box1.add(pwdField);
        box.add(box1);
        box.add(Box.createVerticalStrut(15));

        Box box2 = Box.createHorizontalBox();
        box2.add(new JLabel("新  密  码："));
        JPasswordField newField = new JPasswordField(15);
        newField.setInputVerifier(SystemVerifier.emptyVerify("密码", 6, 20));
        box2.add(newField);
        box.add(box2);
        box.add(Box.createVerticalStrut(15));

        Box box3 = Box.createHorizontalBox();
        box3.add(new JLabel("确认密码："));
        JPasswordField repwdField = new JPasswordField(15);
        repwdField.setInputVerifier(SystemVerifier.emptyVerify("确认密码", 6, 20));
        box3.add(repwdField);
        box.add(box3);
        box.add(Box.createVerticalStrut(15));

        Box box4 = Box.createHorizontalBox();
        box4.add(Box.createHorizontalStrut(30));
        JButton btn = new JButton("提交");
        box4.add(btn);
        box.add(box4);

        btn.addActionListener(e -> {
            if(!pwdField.getInputVerifier().verify(pwdField) || !newField.getInputVerifier().verify(newField) || !repwdField.getInputVerifier().verify(repwdField)) {
                return;
            }
            String password = new String(pwdField.getPassword());
            String newpwd = new String(newField.getPassword());
            String repwd = new String(repwdField.getPassword());
            if(!newpwd.equals(repwd)) {
                JOptionPane.showMessageDialog(btn.getParent(), "两次输入密码不一致", "系统提示", JOptionPane.WARNING_MESSAGE);
                return;
            }
            if(!password.equals(MainFrame.user.getPassword())) {
                JOptionPane.showMessageDialog(btn.getParent(), "原始密码错误", "系统提示", JOptionPane.WARNING_MESSAGE);
                return;
            }
            switch (MainFrame.user.getType()) {
                case 1://管理员
                    AdminDAO.updatePassword(MainFrame.user.getId(), newpwd);
                    break;
                case 2://班导
                    TeacherDAO.updatePassword(MainFrame.user.getId(), newpwd);
                    break;
                case 3://学生
                    StudentDAO.updatePassword(MainFrame.user.getId(), newpwd);
                    break;
                case 4://辅导员
                    CounselorDAO.updatePassword(MainFrame.user.getId(), newpwd);
                    break;
            }
            JOptionPane.showMessageDialog(btn.getParent(), "修改成功，请重新登录", "系统提示", JOptionPane.INFORMATION_MESSAGE);
            MainFrame.frame.setContentPane(new LoginPanel());
        });
    }
}
