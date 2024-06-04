package TankWarGame;

import java.util.Vector;

import static TankWarGame.MyTankGame02.X_MAX_BOUNDARY;
import static TankWarGame.MyTankGame02.Y_MAX_BOUNDARY;

/**
 * @version 1.0
 * superclass for tank
 */
public class Tank {
    private int x;
    private int y;
    private int direct;
    private int speed =5;
    private boolean live = true ;
    private Vector<Tank> tanks = new Vector<>();
    private Vector<Wall> walls = new Vector<>();

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public void moveUp(){
        if(y>0 && !isTouchEnemyTank() && !isTouchWall())
            y-=speed;
    }
    public void moveDown(){
        if(y+60<Y_MAX_BOUNDARY && !isTouchEnemyTank() && !isTouchWall())
            y+=speed;
    }
    public void moveRight(){
        if(x+60<X_MAX_BOUNDARY && !isTouchEnemyTank()&& !isTouchWall() )
            x+=speed;
    }
    public void moveLeft(){
        if(x>0 && !isTouchEnemyTank() && !isTouchWall())
            x-=speed;
    }

    public boolean isTouchEnemyTank() {


        switch (this.getDirect()) {
            case 0:
                for (int i = 0; i < tanks.size(); i++) {
                    Tank tank = tanks.get(i);
                    if (tank != this) {
                        if (tank.getDirect() == 0 || tank.getDirect() == 2) {
                            if (this.getX() >= tank.getX()
                                    && this.getX() <= tank.getX() + 40
                                    && this.getY() >= tank.getY()
                                    && this.getY() <= tank.getY() + 60) {
                                return true;
                            }
                            if (this.getX() + 40 >= tank.getX()
                                    && this.getX() + 40 <= tank.getX() + 40
                                    && this.getY() >= tank.getY()
                                    && this.getY() <= tank.getY() + 60) {
                                return true;
                            }
                        }
                        if (tank.getDirect() == 1 || tank.getDirect() == 3) {
                            if (this.getX() >= tank.getX()
                                    && this.getX() <= tank.getX() + 60
                                    && this.getY() >= tank.getY()
                                    && this.getY() <= tank.getY() + 40) {
                                return true;
                            }
                            if (this.getX() + 40 >= tank.getX()
                                    && this.getX() + 40 <= tank.getX() + 60
                                    && this.getY() >= tank.getY()
                                    && this.getY() <= tank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 1:
                for (int i = 0; i < tanks.size(); i++) {
                    Tank tank = tanks.get(i);
                    if (tank != this) {
                        if (tank.getDirect() == 0 || tank.getDirect() == 2) {
                            if (this.getX() + 60 >= tank.getX()
                                    && this.getX() + 60 <= tank.getX() + 40
                                    && this.getY() >= tank.getY()
                                    && this.getY() <= tank.getY() + 60) {
                                return true;
                            }
                            if (this.getX() + 60 >= tank.getX()
                                    && this.getX() + 60 <= tank.getX() + 40
                                    && this.getY() + 40 >= tank.getY()
                                    && this.getY() + 40 <= tank.getY() + 60) {
                                return true;
                            }
                        }
                        if (tank.getDirect() == 1 || tank.getDirect() == 3) {
                            if (this.getX() + 60 >= tank.getX()
                                    && this.getX() + 60 <= tank.getX() + 60
                                    && this.getY() >= tank.getY()
                                    && this.getY() <= tank.getY() + 40) {
                                return true;
                            }
                            if (this.getX() + 60 >= tank.getX()
                                    && this.getX() + 60 <= tank.getX() + 60
                                    && this.getY() + 40 >= tank.getY()
                                    && this.getY() + 40 <= tank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2:

                for (int i = 0; i < tanks.size(); i++) {
                    Tank tank = tanks.get(i);
                    if (tank != this) {

                        if (tank.getDirect() == 0 || tank.getDirect() == 2) {
                            if (this.getX() >= tank.getX()
                                    && this.getX() <= tank.getX() + 40
                                    && this.getY() + 60 >= tank.getY()
                                    && this.getY() + 60 <= tank.getY() + 60) {
                                return true;
                            }
                            if (this.getX() + 40 >= tank.getX()
                                    && this.getX() + 40 <= tank.getX() + 40
                                    && this.getY() + 60 >= tank.getY()
                                    && this.getY() + 60 <= tank.getY() + 60) {
                                return true;
                            }
                        }
                        if (tank.getDirect() == 1 || tank.getDirect() == 3) {
                            if (this.getX() >= tank.getX()
                                    && this.getX() <= tank.getX() + 60
                                    && this.getY() + 60 >= tank.getY()
                                    && this.getY() + 60 <= tank.getY() + 40) {
                                return true;
                            }
                            if (this.getX() + 40 >= tank.getX()
                                    && this.getX() + 40 <= tank.getX() + 60
                                    && this.getY() + 60 >= tank.getY()
                                    && this.getY() + 60 <= tank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < tanks.size(); i++) {
                    Tank tank = tanks.get(i);
                    if (tank != this) {
                        if (tank.getDirect() == 0 || tank.getDirect() == 2) {
                            if (this.getX() >= tank.getX()
                                    && this.getX() <= tank.getX() + 40
                                    && this.getY() >= tank.getY()
                                    && this.getY() <= tank.getY() + 60) {
                                return true;
                            }
                            if (this.getX() >= tank.getX()
                                    && this.getX() <= tank.getX() + 40
                                    && this.getY() + 40 >= tank.getY()
                                    && this.getY() + 40 <= tank.getY() + 60) {
                                return true;
                            }
                        }
                        if (tank.getDirect() == 1 || tank.getDirect() == 3) {
                            if (this.getX() >= tank.getX()
                                    && this.getX() <= tank.getX() + 60
                                    && this.getY() >= tank.getY()
                                    && this.getY() <= tank.getY() + 40) {
                                return true;
                            }
                            if (this.getX() >= tank.getX()
                                    && this.getX() <= tank.getX() + 60
                                    && this.getY() + 40 >= tank.getY()
                                    && this.getY() + 40 <= tank.getY() + 40) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return  false;
    }
    public boolean isTouchWall() {

        switch (this.getDirect()) {
            case 0:
                for (int i = 0; i < walls.size(); i++) {
                    Wall wall = walls.get(i);
                    if (wall.isLive()) {
                        if (this.getX() >= wall.getX() && this.getX() <= wall.getX() + 35
                                && this.getY() >= wall.getY() && this.getY() <= wall.getY()+35){

                            return true;
                        }

                        if (this.getX()+40 >= wall.getX() && this.getX()+40 <= wall.getX() + 35
                                && this.getY() >= wall.getY() && this.getY() <= wall.getY()+35 ) {

                            return true;
                        }
                    }
                }
                break;
            case 2:

                for (int i = 0; i < walls.size(); i++) {
                    Wall wall = walls.get(i);
                    if (wall.isLive()) {
                        if (this.getX() >= wall.getX() && this.getX() <= wall.getX() + 35
                                && this.getY()+60 >= wall.getY() && this.getY()+60 <= wall.getY()+35){

                            return true;
                        }

                        if (this.getX()+40 >= wall.getX() && this.getX()+40 <= wall.getX() + 35
                                && this.getY()+60 >= wall.getY() && this.getY()+60 <= wall.getY()+35 ) {

                            return true;
                        }
                    }
                }
                break;
            case 1:
                for (int i = 0; i < walls.size(); i++) {
                    Wall wall = walls.get(i);
                    if (wall.isLive()) {
                        if (this.getX()+60 >= wall.getX() && this.getX()+60 <= wall.getX() + 35
                                && this.getY() >= wall.getY() && this.getY() <= wall.getY()+35){

                            return true;
                        }

                        if (this.getX()+60 >= wall.getX() && this.getX()+60 <= wall.getX() + 35
                                && this.getY()+40 >= wall.getY() && this.getY()+40 <= wall.getY()+35 ) {

                            return true;
                        }
                    }
                }
                break;
            case 3:
                for (int i = 0; i < walls.size(); i++) {
                    Wall wall = walls.get(i);
                    if (wall.isLive()) {
                        if (this.getX() >= wall.getX() && this.getX() <= wall.getX()
                                && this.getY() >= wall.getY() && this.getY() <= wall.getY() + 35)
                            return true;
                        if (this.getX() >= wall.getX() && this.getX() <= wall.getX()+35
                                && this.getY()+40 >= wall.getY() && this.getY()+40 <= wall.getY() + 35)
                            return true;
                    }
                }
                break;
        }

        return false;

    }

    public void setTanks(Vector tanks) {
        this.tanks=tanks;
    }
    public void setTank(MyTank tank) {
        tanks.add(tank);
    }
    public int getDirect() {
        return direct;
    }
    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }

    public Vector<Wall> getWall() {
        return walls;
    }

    public void setWall(Vector<Wall> walls) {
        this.walls = walls;
    }
}