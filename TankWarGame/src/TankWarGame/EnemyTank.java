package TankWarGame;

import java.util.Vector;

/**
 * @version 1.0
 * 敌人的坦克
 */
public class EnemyTank extends Tank implements Runnable{
    Shot shot = null;

    Vector<Shot> shots= new Vector<>();

    public EnemyTank(int x, int y){
        super(x, y);
    }

    @Override
    public void run() {
        while (true) {

            if (isLive() && shots.size()<2) {

                switch (getDirect()) {
                    case 0:
                        shot= new Shot(getX() + 20, getY(), 0);
                        break;
                    case 1:
                        shot = new Shot(getX() + 60, getY() + 20, 1);
                        break;
                    case 2:
                        shot = new Shot(getX() + 20, getY() + 60, 2);
                        break;
                    case 3:
                        shot = new Shot(getX(), getY() + 20, 3);
                        break;
                }
                shots.add(shot);

                new Thread(shot).start();
            }
            for(int i=0; i<5; i++) {
                switch (getDirect()) {
                    case 0:
                        moveUp();

                        break;
                    case 1:
                        moveRight();
                        break;
                    case 2:
                        moveDown();

                        break;
                    case 3:
                        moveLeft();

                        break;
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            setDirect((int) (Math.random() * 4));
            if(!isLive()){
                break;
            }

        }
    }
}