package frame.counselor;

import frame.LoginPanel;
import frame.MainFrame;
import frame.PasswordPanel;
import util.SystemConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CounselorPanel extends JPanel {
    private static JDesktopPane contentPanel = new JDesktopPane();

    public CounselorPanel() {
        this.setBounds(0,0, SystemConstants.FRAME_WIDTH, SystemConstants.FRAME_HEIGHT);
        this.setLayout(new BorderLayout());
        this.setVisible(true);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setBounds(0, 0, SystemConstants.FRAME_WIDTH, 50);
        this.add(menuBar, BorderLayout.NORTH);

        contentPanel.removeAll();
        contentPanel.repaint();
        this.add(contentPanel, BorderLayout.CENTER);

        JMenu parentNowMenu = new JMenu("未审核请假记录");
        JMenu parentMenu = new JMenu("请假记录");
        JMenu systemMenu = new JMenu("系统管理");
        menuBar.add(parentNowMenu);
        menuBar.add(parentMenu);
        menuBar.add(systemMenu);
        JMenuItem passwordMenu = new JMenuItem("修改密码");
        JMenuItem logoutMenu = new JMenuItem("退出登录");
        systemMenu.add(passwordMenu);
        systemMenu.add(logoutMenu);

        parentNowMenu.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new CounselorDataNowTablePanel());
            }
        });

        parentMenu.addMouseListener(new MouseAdapter(){
            @Override
            public void mousePressed(MouseEvent e) {
                setContent(new CounselorDataTablePanel());
            }
        });

        logoutMenu.addActionListener(e -> {
            contentPanel.removeAll();
            contentPanel.repaint();
            MainFrame.setContent(new LoginPanel());
        });

        passwordMenu.addActionListener(e -> {
            setContent(new PasswordPanel());
        });
    }

    public static void setContent(JInternalFrame internalFrame) {
        internalFrame.setSize(SystemConstants.FRAME_WIDTH - 15, SystemConstants.FRAME_HEIGHT - 60);
        //内部窗口
        internalFrame.setVisible(true);
        contentPanel.removeAll();
        contentPanel.repaint();
        contentPanel.add(internalFrame);
    }
}
