package TankWarGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


/**
 * @version 1.1
 *
 */
public  class MyTankGame02 extends JFrame {
    MyPanel mp = null;
    public static final int X_MAX_BOUNDARY = 1000;
    public static final int Y_MAX_BOUNDARY = 600;
    public static  char key = '0';

    public static void main(String[] args) {
        new MyTankGame02();
    }
    public MyTankGame02() {
        ImageIcon bg=new ImageIcon("src/resources/background.png");
        JLabel label=new JLabel(bg);
        label.setSize(bg.getIconWidth(),bg.getIconHeight());
        this.getLayeredPane().add(label,new Integer(Integer.MIN_VALUE));
        JPanel pan=(JPanel)this.getContentPane();
        pan.setOpaque(false);
        pan.setLayout(new FlowLayout());
        JButton button1 = new JButton("New Game");
        JButton button2 = new JButton("Continue Game");
        this.setTitle("TankWar");
        this.setBounds(200,200,bg.getIconWidth(),bg.getIconHeight());
        this.setVisible(true);
        this.setLayout(new FlowLayout());
        this.add(button1);
        this.add(button2);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setGame();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                key='1';
                setGame();
            }
        });
    }

    public void setGame(){
        closeThis();
        JFrame frame = new JFrame();
        mp = new MyPanel(key);
        new Thread(mp).start();
        frame.setTitle("TankWar");
        frame.add(mp);
        frame.setSize(X_MAX_BOUNDARY + 15 + 300, Y_MAX_BOUNDARY + 35);
        frame.addKeyListener(mp);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                Recorder.keepRecord();
                System.exit(0);
            }
        });

    }
    public void closeThis() {
        this.dispose();
    }
}