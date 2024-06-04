package TankWarGame;

import java.util.Vector;

/**
 * @version 1.0
 * my tank
 */
public class MyTank extends Tank {
    Shot shot = null;
    Vector<Shot> shots= new Vector<>();
    public MyTank(int x, int y) {
        super(x, y);
    }
    public void shotMyTank(){

        switch(getDirect()){
            case 0:
                shot = new Shot(getX()+20,getY(),getDirect());
                break;
            case 1:
                shot = new Shot(getX()+60,getY()+20,getDirect());
                break;
            case 2:
                shot = new Shot(getX()+20,getY()+60,getDirect());
                break;
            case 3:
                shot = new Shot(getX(),getY()+20,getDirect());
                break;
        }
        shots.add(shot);
        new Thread(shot).start();
    }
}