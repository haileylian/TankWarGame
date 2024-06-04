package TankWarGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Vector;


/**
 * @version 1.0
 * the area for tank war
 */
public class MyPanel extends JPanel implements KeyListener, Runnable {

    MyTank mytank = null;


    Vector<EnemyTank> enemyTanks = new Vector<>();

    Vector<Wall> walls = new Vector<>();

    int enemyTanksSize = 6;

    Vector<Bomb> bombs = new Vector<>();

    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel(char key) {


        Recorder.setEnemyTanks(enemyTanks);

        mytank = new MyTank(100, 500);
        Recorder.setMytank(mytank);

        File file = new File(Recorder.getRecotderfile()+"");
        if (!file.exists()) {
            System.out.println("N archive. Start New Game");
            key = '0';
        }
        if (key == '0') {

            for (int i = 0; i < enemyTanksSize; i++) {
                EnemyTank enemyTank = new EnemyTank(100 * (i + 1), 0);
                enemyTank.setDirect(2);

                Shot shot = new Shot(enemyTank.getX() + 60, enemyTank.getY() + 20, enemyTank.getDirect());
                enemyTank.shots.add(shot);

                new Thread(shot).start();

                new Thread(enemyTank).start();
                enemyTanks.add(enemyTank);

                enemyTank.setTanks(enemyTanks);


                enemyTank.setWall(walls);

            }

            mytank.setTanks(enemyTanks);
        } else if (key == '1') {
            Recorder.getNodesAndEnemyTankRec();
            Vector<Noder> noders = Recorder.getNones();
            mytank.setX(noders.get(0).getX());
            mytank.setY(noders.get(0).getY());
            for (int i = 1; i < noders.size(); i++) {
                Noder noder = noders.get(i);
                EnemyTank enemyTank = new EnemyTank(noder.getX(), noder.getY());
                enemyTank.setDirect(noder.getDirect());

                Shot shot = new Shot(enemyTank.getX() + 60, enemyTank.getY() + 20, enemyTank.getDirect());
                enemyTank.shots.add(shot);

                new Thread(shot).start();

                new Thread(enemyTank).start();
                enemyTanks.add(enemyTank);

                enemyTank.setTanks(enemyTanks);
//

                enemyTank.setWall(walls);
            }

            mytank.setTanks(enemyTanks);
        }


        GameMap.drawWall(walls);

        mytank.setWall(walls);


        image1 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/resources/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/resources/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().getImage(MyPanel.class.getResource("/resources/bomb_3.gif"));

        new AePlayWave("src/resources/music.wav").start();
    }

    public void showInfo(Graphics g) {
        g.setColor(Color.BLACK);
        Font font = new Font("Arial", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("You have destoryed enemy tanks:  ", MyTankGame02.X_MAX_BOUNDARY+5, 30);
        drawTank(MyTankGame02.X_MAX_BOUNDARY+50, 60, g, 0, 1);
        g.setColor(Color.BLACK);
        g.drawString(Recorder.getAllEnemyTankNum() + "", MyTankGame02.X_MAX_BOUNDARY+100, 100);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, MyTankGame02.X_MAX_BOUNDARY, MyTankGame02.Y_MAX_BOUNDARY);
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (bomb.getLife() > 6) {
                g.drawImage(image1, bomb.getX(), bomb.getY(), 60, 60, this);
            } else if (bomb.getLife() > 3) {
                g.drawImage(image2, bomb.getX(), bomb.getY(), 60, 60, this);
            } else {
                g.drawImage(image3, bomb.getX(), bomb.getY(), 60, 60, this);
            }

            bomb.lifeDown();

            if (bomb.getLife() == 0) {
                bombs.remove(bomb);
            }
        }

        if (mytank.isLive() && mytank != null) {
            drawTank(mytank.getX(), mytank.getY(), g, mytank.getDirect(), 0);
        } else {
            System.out.println("Game over 游戏结束！");

        }


        for (int i = 0; i < mytank.shots.size(); i++) {
            Shot shot = mytank.shots.get(i);
            if (shot != null && shot.isLive()) {

                g.setColor(Color.GREEN);
                g.fillOval(shot.getX(), shot.getY(), 4, 4);
            } else {

                mytank.shots.remove(shot);
            }
        }

        for (EnemyTank enemyTank : enemyTanks) {
            if (enemyTank.isLive()) {

                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);

                for (int j = 0; j < enemyTank.shots.size(); j++) {

                    Shot shot = enemyTank.shots.get(j);
                    if (shot.isLive()) {
                        g.draw3DRect(shot.getX(), shot.getY(), 2, 2, false);
                    } else {
                        enemyTank.shots.remove(shot);
                    }
                }
            }
        }

        showInfo(g);

        for (Wall wall : walls) {
            drawWall(wall.getX(),wall.getY(),g,wall.getType());
        }
    }


    /**
     * @param x The x-coordinate of the upper-left corner of the tank.
     * @param y The y-coordinate of the upper-left corner of the tank.
     * @param g brush
     * @param direct Tank direction 0: up 1 right 2 down 3 left
     * @param type Tank type
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        switch (type) {
            case 0:
                g.setColor(Color.cyan);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
        }
        /**
         * fill3DRect 画3D矩形
         * fillOval 画圆形
         * drawLine 画线段
         */

        switch (direct) {
            case 0:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            case 1:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            case 3:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
            default:
                System.out.println("Error. Not 0~3");
        }
    }
    public void drawWall(int x, int y, Graphics g,int  type){
        switch (type) {
            case 0:
                g.setColor(Color.PINK);
                break;
            case 1:
                g.setColor(Color.white);
                break;
            case 2:
                g.setColor(Color.BLUE);
                break;
        }
        g.fill3DRect(x,y,35, 35, false);
    }

    public void hitMyTank() {
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.shots.size() != 0) {
                hitTank(enemyTank.shots, mytank);

            }
        }
    }

    public void hitEnemyTank() {
        if (mytank.shots.size() != 0) {
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                hitTank(mytank.shots, enemyTank);
            }
        }
    }

    public void hitTank(Vector<Shot> shots, Tank tank) {
        Shot fa = null;
        for (int i = 0; i < shots.size(); i++) {
            Shot s = shots.get(i);
            switch (tank.getDirect()) {
                case 0:
                case 2:
                    if (s.getX() > tank.getX() && s.getX() < tank.getX() + 40
                            && s.getY() > tank.getY() && s.getY() < tank.getY() + 60) {
                        new AePlayWave("src/resources/music2.wav").start();
                        s.setLive(false);
                        tank.setLive(false);
                        Bomb b = new Bomb(tank.getX(), tank.getY());
                        bombs.add(b);
                        if (tank instanceof EnemyTank) {
                            Recorder.addAllEnemyTankNum();
                            enemyTanks.remove(tank);
                        }
                    }
                    break;
                case 1:
                case 3:
                    if (s.getX() > tank.getX() && s.getX() < tank.getX() + 60
                            && s.getY() > tank.getY() && s.getY() < tank.getY() + 40) {
                        new AePlayWave("src/resources/music2.wav").start();
                        s.setLive(false);
                        tank.setLive(false);
                        Bomb b = new Bomb(tank.getX(), tank.getY());
                        bombs.add(b);
                        if (tank instanceof EnemyTank) {
                            Recorder.addAllEnemyTankNum();
                            enemyTanks.remove(tank);
                        }
                    }
                    break;
            }
        }
    }
    public void hitMyWall(){
        if (mytank.shots.size() != 0) {
            for (int i = 0; i < walls.size(); i++) {
                Wall shatterableWall = walls.get(i);
                hitWall(mytank.shots, shatterableWall);
            }
        }
    }
    public void hitEnemyWall(){
        for (int i = 0; i < enemyTanks.size(); i++) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.shots.size() != 0) {
                for (int j = 0; j < walls.size(); j++) {
                    Wall shatterableWall = walls.get(j);
                    hitWall(enemyTank.shots, shatterableWall);
                }
            }
        }
    }
    public void hitWall(Vector<Shot> shots, Wall wall) {
        if (wall.getType() == 2){
            return;
        }
        Shot fa = null;
        for (int i = 0; i < shots.size(); i++) {
            Shot s = shots.get(i);
            if (s.getX() > wall.getX()-5 && s.getX() < wall.getX() + 35
                    && s.getY() > wall.getY()-5 && s.getY() < wall.getY() + 35) {
                new AePlayWave("src/resources/music2.wav").start();
                s.setLive(false);
                if(wall.getType()!=1){
                    wall.setLive(false);
                    Bomb b = new Bomb(wall.getX(), wall.getY());
                    bombs.add(b);
                    if (wall instanceof Wall) {
                        walls.remove(wall);
                    }
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_S) {
            mytank.moveDown();
            mytank.setDirect(2);
        } else if (e.getKeyCode() == KeyEvent.VK_W) {
            mytank.moveUp();
            mytank.setDirect(0);
        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            mytank.moveLeft();
            mytank.setDirect(3);
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            mytank.moveRight();
            mytank.setDirect(1);
        }


        if (e.getKeyCode() == KeyEvent.VK_J) {
            System.out.println("用户按下了J, 开始射击.");
            mytank.shotMyTank();

            System.out.println(mytank.shots.size());
        }

        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException("MyPanel.run() is Exception");
            }

            hitEnemyTank();
            hitMyTank();
            hitMyWall();
            hitEnemyWall();
            this.repaint();
        }
    }
}