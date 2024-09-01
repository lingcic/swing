package frame;

import pojo.User;
import util.SystemConstants;

import javax.swing.*;

public class MainFrame {
    public static final JFrame frame = new JFrame("学生请假管理系统");
    public static User user;
    public static void main(String[] args) {
        frame.setSize(SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);
        frame.setLocationRelativeTo(null);
        frame.setContentPane(new LoginPanel());
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void setContent(JPanel panel) {
        frame.setContentPane(panel);
    }
}