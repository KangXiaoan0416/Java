package main.java.utils;

import com.sun.istack.internal.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * 图形界面练习程序
 *
 * @author: 康小安
 * @createDate: 2021/5/17 14:28
 */
public class Test1 {
    private static int width = 700;
    private static int height = 550;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    /**
     * 创建界面
     *
     * @author: 康小安
     * @createDate: 2021/5/17 14:38
     * @return: void
     */
    private static void createAndShowGUI() {
        JFrame frame = createFrame();
        frame = createMenu(frame);
        frame.setVisible(true);
    }

    /**
     * 创建frame面板
     *
     * @author: 康小安
     * @createDate: 2021/5/17 18:47
     * @return: javax.swing.JFrame
     */
    private static JFrame createFrame() {
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("测试程序");
        frame.setLayout(new BorderLayout());

        frame = initFrameSize(frame, width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        return frame;
    }

    private static JPanel createPanel() {
        JPanel panel = new JPanel();
        return panel;
    }

    /**
     * 创建菜单栏
     *
     * @param frame 窗口对象
     * @author: 康小安
     * @createDate: 2021/5/17 19:29
     * @return: void
     */
    private static JFrame createMenu(JFrame frame) {
        JMenuBar jMenuBar = new JMenuBar();

        jMenuBar.add(createFileMenu());
        jMenuBar.add(createEditMenu());

        jMenuBar.setVisible(true);

        frame.add(jMenuBar, BorderLayout.NORTH);

        return frame;

    }

    private static JMenu createFileMenu() {
        JMenu fileMenu = new JMenu("文件F");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem item = new JMenuItem("新建(N)", KeyEvent.VK_N);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        fileMenu.add(item);

        item = new JMenuItem("打开(O)", KeyEvent.VK_O);
        item.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        fileMenu.add(item);

        return fileMenu;
    }

    private static JMenu createEditMenu() {
        JMenu menu = new JMenu("编辑(E)");
        menu.setMnemonic(KeyEvent.VK_E);

        JMenuItem item = new JMenuItem("撤销(U)", KeyEvent.VK_U);
        item.setEnabled(false);
        menu.add(item);

        menu.addSeparator();
        item = new JMenuItem("剪贴(T)", KeyEvent.VK_T);
        menu.add(item);

        item = new JMenuItem("复制(C)", KeyEvent.VK_C);
        menu.add(item);
        menu.addSeparator();

        JCheckBoxMenuItem cbMenuItem = new JCheckBoxMenuItem("自动换行");
        menu.add(cbMenuItem);

        return menu;
    }

    /**
     * 工具栏菜单
     * @author: 康小安
     * @createDate: 2021/5/18 12:26
     * @return: javax.swing.JMenu
     */
    private static JMenu createToolMenu() {
        JMenu menu = new JMenu("工具栏");


        JMenuItem item = new JMenuItem("工具1");
        menu.add(item);
        menu.addSeparator();

        item = new JMenuItem("工具2");
        menu.add(item);
        menu.addSeparator();

        item = new JMenuItem("工具3");
        menu.add(item);
        menu.addSeparator();

        return menu;
    }

    private static void layout() {

    }

    /**
     * 初始化界面大小
     *
     * @param frame  界面
     * @param width  宽度
     * @param height 高度
     * @author: 康小安
     * @createDate: 2021/5/18 12:20
     * @return: javax.swing.JFrame
     */
    private static JFrame initFrameSize(JFrame frame, @NotNull int width, @NotNull int height) {
        assert (width == 0 || height == 0);

        int[] screen = getScreen();
        int x = (screen[0] - width) / 2;
        int y = (screen[1] - height) / 2;
        frame.setBounds(x, y, width, height);

        return frame;
    }

    private static int[] getScreen() {
        int[] screen = new int[2];
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;

        screen[0] = screenWidth;
        screen[1] = screenHeight;
        return screen;
    }

    /**
     * 界面练习
     *
     * @author: Kangxiaoan
     * @createDate: 2021/5/17 14:37
     */
    public static class Frame {

    }
}

