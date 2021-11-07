package game;

import game.SFX.Music;
import game.controls.ButtonClick;
import game.controls.Buttons;
import game.controls.CharacterController;
import game.gameView.GameView;
import game.gameView.GiveFocus;
import game.gameView.Tracker;
import game.levels.*;
import javax.swing.JFrame;
import java.awt.*;

/**
 * A world with some bodies.
 */
public class Game {

    /** The World in which the bodies move and interact. */
    private GameLevel level;

    /** A graphical display of the world (a specialised JPanel). */
    private GameView view;

    private CharacterController controller;

    private Music music;

    private ButtonClick buttonClick;

    /** Initialise a new Game. */
    public Game() {

        // make the world
        level = new Level1(this);
        level.populate(this);
        music = new Music();
        music.getGameMusic().get(0).loop();

        // make a view
        view = new GameView(level, 800, 650,music,this);
        view.setZoom(20);

        buttonClick = new ButtonClick(level,view,music);
        // uncomment this to draw a 1-metre grid over the view
        // view.setGridResolution(1);
        //where all the Listener has been added, allowing control, track the character every time,etc.
        controller = new CharacterController(level.getProtagonist(), level.getEnemy(), level.getEnemy2(), level.getBoss());
        view.addKeyListener(controller);
        view.addMouseListener(new GiveFocus(view));
        level.addStepListener(new Tracker(view, level.getProtagonist()));
        view.addMouseListener(buttonClick);
        // add the view to a frame (Java top level window)
        final JFrame frame = new JFrame("Red Assassin");
        frame.add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Buttons buttons = new Buttons(level, level.getGame());
        frame.add(buttons,BorderLayout.SOUTH);
        frame.setLocationByPlatform(true);
        // don't let the frame be resized
        frame.setResizable(false);
        // size the frame to fit the world view
        frame.pack();
        // finally, make the frame visible
        frame.setVisible(true);
        // uncomment this to make a debugging view
        //JFrame debugView = new DebugViewer(level, 1000, 1000);
        // start our game world simulation!z
        level.start();
    }

    /**
     * a getter method to indicate which level the user is in
     * @return - level to allow which object is to be saved
     */
    public GameLevel getLevel(){
        return level;
    }

    /**
     * use to allocate the state of the level
     * @param level - Gamelevel class is passed through
     */
    public void setLevel(GameLevel level){
        this.level.stop();
        this.level = level;
        view.setWorld(this.level);//updates the world
        this.level.addStepListener(new Tracker(view, level.getProtagonist()));//apply the tracker
        controller.updateControls(level.getProtagonist(), level.getEnemy(), level.getEnemy2(), getLevel().getBoss());//update the controls
        buttonClick.buttonUpdate(level,view,music);//update the button
        //stop all music
        music.getGameMusic().get(1).stop();
        music.getGameMusic().get(2).stop();
        music.getGameMusic().get(3).stop();
        music.getGameMusic().get(4).stop();
        music.getGameMusic().get(0).stop();
        if(level instanceof  Level1){
            //setting the background for level 1 and music
            view.setBackground(1);
            music.getGameMusic().get(0).loop();
        }
        if(level instanceof  Level2){
            //setting the background for level 2 and music
            view.setBackground(2);
            music.getGameMusic().get(1).loop();
        }
        if(level instanceof  Level3){
            //setting the background for level 3 and music
            view.setBackground(3);
            music.getGameMusic().get(2).loop();
        }
        if(level instanceof  Level4){
            //setting the background for level 4 and music
            view.setBackground(4);
            music.getGameMusic().get(3).loop();
        }
        if(level instanceof FinalLevel){
            //setting the background for level 5 and music
            view.setBackground(5);
            music.getGameMusic().get(2).loop();
        }
        this.level.start();
    }
    /**
     * a method of progressing to the next level
     */
    public void goToNextStage(){
        MainCharacter oldPlayer = level.getProtagonist();
        if(level instanceof Level1){
            //set this update for level 2
            level.stop();
            level = new Level2(this);
            level.populate(this);
            music.getGameMusic().get(0).stop();
            music.getGameMusic().get(1).loop();
            view.setWorld(level);
            view.setBackground(2);
            level.addStepListener(new Tracker(view, level.getProtagonist()));
            controller.updateControls(level.getProtagonist(), level.getEnemy(), level.getEnemy2(), level.getBoss());
            level.getProtagonist().setHeart(oldPlayer.getCurrentHealth());
            level.getProtagonist().setScore(oldPlayer.getScore());
            buttonClick.buttonUpdate(level,view,music);
            level.start();
        } else if (level instanceof Level2){
            //set these updates for level 3
            level.stop();
            level = new Level3(this);
            level.populate(this);
            music.getGameMusic().get(1).stop();
            music.getGameMusic().get(2).loop();
            view.setWorld(level);
            view.setBackground(3);
            level.addStepListener(new Tracker(view, level.getProtagonist()));
            controller.updateControls(level.getProtagonist(), level.getEnemy(), level.getEnemy2(), level.getBoss());
            level.getProtagonist().setHeart(oldPlayer.getCurrentHealth());
            level.getProtagonist().setScore(oldPlayer.getScore());
            buttonClick.buttonUpdate(level,view,music);
            level.start();
        }else if(level instanceof Level3) {
            //set these updates for level 4
            level.stop();
            level = new Level4(this);
            level.populate(this);
            music.getGameMusic().get(2).stop();
            music.getGameMusic().get(3).loop();
            view.setWorld(level);
            view.setBackground(4);
            level.addStepListener(new Tracker(view, level.getProtagonist()));
            controller.updateControls(level.getProtagonist(), level.getEnemy(), level.getEnemy2(), level.getBoss());
            level.getProtagonist().setHeart(oldPlayer.getCurrentHealth());
            level.getProtagonist().setScore(oldPlayer.getScore());
            buttonClick.buttonUpdate(level,view,music);
            level.start();
        }else if (level instanceof Level4){
            //set these updates for level 5
            level.stop();
            level = new FinalLevel(this);
            level.populate(this);
            music.getGameMusic().get(3).stop();
            music.getGameMusic().get(2).loop();
            view.setWorld(level);
            view.setBackground(5);
            level.addStepListener(new Tracker(view, level.getProtagonist()));
            controller.updateControls(level.getProtagonist(), level.getEnemy(), level.getEnemy2(), level.getBoss());
            level.getProtagonist().setHeart(oldPlayer.getCurrentHealth());
            level.getProtagonist().setScore(oldPlayer.getScore());
            buttonClick.buttonUpdate(level,view,music);
            level.start();
        }else if (level instanceof FinalLevel){
            //after level 5 the game is complete
            System.out.println("Game Complete");
            System.exit(0);
        }
    }

    /**
     * a setter method which allows users to go back a level
     */
    public void goToBackLevel(){
        MainCharacter oldPlayer = level.getProtagonist();
        if (level instanceof FinalLevel) {
            //set these updates for level 4
            level.stop();
            level = new Level4(this);
            level.populate(this);
            music.getGameMusic().get(2).stop();
            music.getGameMusic().get(3).loop();
            view.setWorld(level);
            view.setBackground(4);
            level.addStepListener(new Tracker(view, level.getProtagonist()));
            controller.updateControls(level.getProtagonist(), level.getEnemy(), level.getEnemy2(), level.getBoss());
            level.getProtagonist().setHeart(oldPlayer.getCurrentHealth());
            level.getProtagonist().setScore(oldPlayer.getScore());
            buttonClick.buttonUpdate(level,view,music);
            level.start();
        }else if(level instanceof Level4){
            //set these updates for level 3
            level.stop();
            level = new Level3(this);
            level.populate(this);
            music.getGameMusic().get(3).stop();
            music.getGameMusic().get(2).loop();
            view.setWorld(level);
            view.setBackground(3);
            level.addStepListener(new Tracker(view, level.getProtagonist()));
            controller.updateControls(level.getProtagonist(), level.getEnemy(), level.getEnemy2(), level.getBoss());
            level.getProtagonist().setHeart(oldPlayer.getCurrentHealth());
            level.getProtagonist().setScore(oldPlayer.getScore());
            buttonClick.buttonUpdate(level,view,music);
            level.start();
        }else if(level instanceof Level3){
            //set these updates for level 2
            level.stop();
            level = new Level2(this);
            level.populate(this);
            music.getGameMusic().get(2).stop();
            music.getGameMusic().get(1).loop();
            view.setWorld(level);
            view.setBackground(2);
            level.addStepListener(new Tracker(view, level.getProtagonist()));
            controller.updateControls(level.getProtagonist(), level.getEnemy(), level.getEnemy2(), level.getBoss());
            level.getProtagonist().setHeart(oldPlayer.getCurrentHealth());
            level.getProtagonist().setScore(oldPlayer.getScore());
            buttonClick.buttonUpdate(level,view,music);
            level.start();
        }else if (level instanceof  Level2){
            //set these updates for level 1
            level.stop();
            level = new Level1(this);
            level.populate(this);
            music.getGameMusic().get(1).stop();
            music.getGameMusic().get(0).loop();
            view.setWorld(level);
            view.setBackground(1);
            level.addStepListener(new Tracker(view, level.getProtagonist()));
            controller.updateControls(level.getProtagonist(), level.getEnemy(), level.getEnemy2(), level.getBoss());
            level.getProtagonist().setHeart(oldPlayer.getCurrentHealth());
            level.getProtagonist().setScore(oldPlayer.getScore());
            buttonClick.buttonUpdate(level,view,music);
            level.start();
        }
    }

    /**
     * method which restarts the level
     */
    public void restart() {
        if (level instanceof Level1) {
            //restart level 1
            level.stop();
            level = new Level1(this);
            level.populate(this);
            music.getGameMusic().get(0).stop();
            music.getGameMusic().get(0).loop();
            view.setWorld(level);
            view.setBackground(1);
            level.addStepListener(new Tracker(view, level.getProtagonist()));
            controller.updateControls(level.getProtagonist(), level.getEnemy(), level.getEnemy2(), level.getBoss());
            buttonClick.buttonUpdate(level,view,music);
            level.start();
        }if (level instanceof Level2) {
            //restart 2
            level.stop();
            level = new Level2(this);
            level.populate(this);
            music.getGameMusic().get(2).stop();
            music.getGameMusic().get(1).loop();
            view.setWorld(level);
            view.setBackground(2);
            level.addStepListener(new Tracker(view, level.getProtagonist()));
            controller.updateControls(level.getProtagonist(), level.getEnemy(), level.getEnemy2(), level.getBoss());
            buttonClick.buttonUpdate(level,view,music);
            level.start();
        }
        if (level instanceof Level3) {
            //restart level 3
            level.stop();
            level = new Level3(this);
            level.populate(this);
            music.getGameMusic().get(2).stop();
            music.getGameMusic().get(2).loop();
            view.setWorld(level);
            view.setBackground(3);
            level.addStepListener(new Tracker(view, level.getProtagonist()));
            controller.updateControls(level.getProtagonist(), level.getEnemy(), level.getEnemy2(), level.getBoss());
            buttonClick.buttonUpdate(level,view,music);
            level.start();
        }
        if (level instanceof Level4) {
            //restart level 4
            level.stop();
            level = new Level4(this);
            level.populate(this);
            music.getGameMusic().get(3).stop();
            music.getGameMusic().get(3).loop();
            view.setWorld(level);
            view.setBackground(4);
            level.addStepListener(new Tracker(view, level.getProtagonist()));
            controller.updateControls(level.getProtagonist(), level.getEnemy(), level.getEnemy2(), level.getBoss());
            buttonClick.buttonUpdate(level,view,music);
            level.start();
        }
        if(level instanceof FinalLevel){
            //restart the final level
            level.stop();
            level = new FinalLevel(this);
            level.populate(this);
            music.getGameMusic().get(2).stop();
            music.getGameMusic().get(2).loop();
            view.setWorld(level);
            view.setBackground(5);
            level.addStepListener(new Tracker(view, level.getProtagonist()));
            controller.updateControls(level.getProtagonist(), level.getEnemy(), level.getEnemy2(), level.getBoss());
            buttonClick.buttonUpdate(level,view,music);
            level.start();
        }
    }


    /** Run the game. */
    public static void main(String[] args) {

        new Game();
    }


}
