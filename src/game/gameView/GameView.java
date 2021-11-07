package game.gameView;
import city.cs.engine.UserView;
import game.Game;
import game.GameLevel;
import game.MainCharacter;
import game.SFX.Music;
import game.levels.*;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GameView extends UserView {

    private ArrayList<Image> background= new ArrayList<>();
    private Image backGround;
    private GameLevel level;
    private int ButtonX = 750;
    private int ButtonY = 10;
    private boolean isPause = false;
    private boolean isGameRunning = true;
    private boolean isVolClicked = false;
    private Music musicimage;
    private Game game;
    private int heathsize =200;
    /**
     * This class initialises the game view
     * (e.g size of window and the background image).
     * @param level Parameter w takes the value World
     * @param width Parameter width takes values of int for the width of the window
     * @param height Parameter height takes values of int for the height of the game window
     */
    public GameView(GameLevel level, int width, int height,Music musicimage,Game game) {
        super(level, width, height);
        this.level = level;
        this.musicimage = musicimage;
        this.game = game;
        //this is the section where the images are being add to the array list
        background.add(new ImageIcon("data/images/levelBackground/level1.png").getImage());
        background.add(new ImageIcon("data/images/levelBackground/background.jpg").getImage());
        background.add(new ImageIcon("data/images/levelBackground/cityScape.jpg").getImage());
        background.add(new ImageIcon("data/images/levelBackground/level3.jpg").getImage());
        background.add(new ImageIcon("data/images/levelBackground/lavaBackground.jpg").getImage());
        backGround = background.get(0);
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(backGround, -500, -100,this);
    }
    @Override
    protected void paintForeground(Graphics2D g){
        MainCharacter protagonist = level.getProtagonist();
        //a point system created, retrieving the characters score and showcasing it
        g.setFont(new Font("Courier New", Font.BOLD, 30));
        g.setColor(Color.white);
        g.drawString("Score:"+protagonist.getScore(),315,30);

        if(game.getLevel() instanceof Level1){
            //in level 1 write this text to help the user understand what to do in the level
            g.setFont(new Font("Cochin", Font.BOLD, 20));
            g.setColor(Color.white);
            g.drawString("[!]Collect all coins to go to next level[!]",215,60);
        }else if(game.getLevel() instanceof Level2){
            //in level 2 write this text to help the user understand what to do in the level
            g.setFont(new Font("Cochin", Font.BOLD, 20));
            g.setColor(Color.white);
            g.drawString("[!]Eliminate the enemy and obtain a key to go to next level[!]",150,60);
        }else if (game.getLevel() instanceof Level3){
            //in level 3 write this text to help the user understand what to do in the level
            g.setFont(new Font("Cochin", Font.BOLD, 20));
            g.setColor(Color.white);
            g.drawString("[!]Obtain at least 5 coins & a key to progress[!]",190,60);
        }else if (game.getLevel() instanceof Level4){
            //in level 4 write this text to help the user understand what to do in the level
            g.setFont(new Font("Cochin", Font.BOLD, 20));
            g.setColor(Color.white);
            g.drawString("[!]Collect all coins & a key to progress[!]",200,60);
        } else if(game.getLevel() instanceof FinalLevel) {
            //in the final level showcase the user with the boss health system
            g.setPaint(new Color(0, 0, 0));
            g.fillRoundRect(300, 50, 200, 20, 20, 50);
            g.setPaint(new Color(200, 10, 50));
            g.fillRoundRect(300, 50, heathsize, 20, 20, 50);
            g.drawImage(new ImageIcon("data/images/bossIcon.png").getImage(),240,20,80,80,this);
            //for everytime the boss gets hit from a projectile decrease its health by lowering the size of the width
            if (game.getLevel().getBoss().getBossHealth() == 9) {
                heathsize = 180;
            } else if (game.getLevel().getBoss().getBossHealth() == 8) {
                heathsize = 160;
            } else if (game.getLevel().getBoss().getBossHealth() == 7) {
                heathsize = 140;
            } else if (game.getLevel().getBoss().getBossHealth() == 6) {
                heathsize = 120;
            } else if (game.getLevel().getBoss().getBossHealth() == 5) {
                heathsize = 100;
            } else if (game.getLevel().getBoss().getBossHealth() == 4) {
                heathsize = 80;
            } else if (game.getLevel().getBoss().getBossHealth() == 3) {
                heathsize = 60;
            } else if (game.getLevel().getBoss().getBossHealth() == 2) {
                heathsize = 40;
            } else if (game.getLevel().getBoss().getBossHealth() == 1) {
                heathsize = 20;
            } else if (game.getLevel().getBoss().getBossHealth() == 0) {
                heathsize = 0;
            }
        }
        if(level.getProtagonist().getCurrentHealth() == 3) {
            //if the protagonist health is 3, show this image
            g.drawImage(
                    new ImageIcon("data/images/interface/fullhealth.png").getImage(),
                    0, -80, 200, 200, this);
        }
        //if the protagonist health is 2, showcase this image
        if(level.getProtagonist().getCurrentHealth()==2){
            g.drawImage(
                    new ImageIcon("data/images/interface/2health.png").getImage(),
                    0,-80,200,200,this);
        }
        //if the protagonist health is 1, showcase this image
        if(level.getProtagonist().getCurrentHealth()==1){
            g.drawImage(
                    new ImageIcon("data/images/interface/1health.png").getImage(),
                    0,-80,200,200,this);
        }
        //if the protagonist health is 0, showcase this image
        if(level.getProtagonist().getCurrentHealth()<=0){
            g.drawImage(
                    new ImageIcon("data/images/interface/0health.png").getImage(),
                    0,-80,200,200,this);
        }
        //a function of decreasing and increasing the volume of the background music of the game
        if(musicimage.getVol() == 1){
            g.drawImage(
                    new ImageIcon("data/images/interface/volume3.png").getImage(),
                    550,-50,150,150,this);
        }else if(musicimage.getVol() == 0.52){
            g.drawImage(
                    new ImageIcon("data/images/interface/volume2.png").getImage(),
                    550,-50,150,150,this);
        }else if(musicimage.getVol() <=  0.040000000000000036 ){
            g.drawImage(
                    new ImageIcon("data/images/interface/volume1.png").getImage(),
                    550, -50, 150, 150, this);
        }else if(musicimage.getVol() == 1.4800000000000004){
            g.drawImage(
                    new ImageIcon("data/images/interface/volume4.png").getImage(),
                    550, -50, 150, 150, this);
        }else if(musicimage.getVol() == 1.9600000000000009){
            g.drawImage(
                    new ImageIcon("data/images/interface/volume5.png").getImage(),
                    550, -50, 150, 150, this);
        }

        //GUI attribute of keys and coins, to showcase how much the user have
        g.drawImage(new ImageIcon("data/images/interface/coin.png").getImage(),
                -18,10,100,100,this);
        g.drawImage(new ImageIcon("data/images/interface/key.png").getImage(),
                3,60,80,80,this);
        g.setFont(new Font("Courier New", Font.BOLD, 30));
        g.setColor(Color.white);
        g.drawString("x"+protagonist.getCoinCounter(),50,70);
        g.drawString("x"+protagonist.getKey(),50,110);
        if(isPause == true){
            g.drawImage(new ImageIcon("data/images/interface/pause.jpg").getImage(),-100,-50,1000,700,this);
        }
        g.drawImage(new ImageIcon("data/images/buttons/Quit.png").getImage(),ButtonX,ButtonY,30,30,this);
        g.drawImage(new ImageIcon("data/images/buttons/Play.png").getImage(),ButtonX,ButtonY+40,30,30,this);
        g.drawImage(new ImageIcon("data/images/buttons/Pause.png").getImage(),ButtonX,ButtonY+80,30,30,this);
        g.drawImage(new ImageIcon("data/images/buttons/volume.png").getImage(),ButtonX,ButtonY+120,30,30,this);
        if(isVolClicked ==true){
            g.drawImage(new ImageIcon("data/images/buttons/Plus.png").getImage(),ButtonX,ButtonY+160,30,30,this);
            g.drawImage(new ImageIcon("data/images/buttons/Minus.png").getImage(),ButtonX,ButtonY+200,30,30,this);
        }
    }
    public void setBackground(int level){
        backGround = background.get(level-1);
    }
    public int getButtonX(){
        return ButtonX;
    }
    public int getButtonY(){
        return ButtonY;
    }
    public void setUnpause(boolean isPause){
        this.isPause = isPause;
    }
    public void setIsGameRunning(boolean isGameRunning){
        this.isGameRunning = isGameRunning;
    }
    public boolean getIsGamePause(){
        return isPause;
    }
    public boolean getIsGameRunning(){
        return isGameRunning;
    }
    public void setVolClicked(boolean isVolClicked){
        this.isVolClicked = isVolClicked;
    }
    public boolean getisVolClicked(){
        return isVolClicked;
    }
}