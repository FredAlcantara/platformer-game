package game.controls;

import game.GameLevel;
import game.SFX.Music;
import game.gameView.GameView;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ButtonClick extends MouseAdapter {
    private GameLevel level;
    private GameView gameView;
    private Music musicVol;

    /**
     *   //fields that will be adjust from the created buttons
     * @param level - this passes in the Gamelevel abstract class
     * @param gameView - this constructor passes the GameView class
     * @param musicVol - this passes in the Music class
     */
    public ButtonClick(GameLevel level, GameView gameView, Music musicVol) {
        this.level = level;
        this.gameView = gameView;
        this.musicVol = musicVol;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //a boolean function which indicated whether the user is clicking on the position of the buttons
        boolean clickPlay = buttonSpace(e, gameView.getButtonX(), gameView.getButtonY() + 40);
        boolean pausePlay = buttonSpace(e, gameView.getButtonX(), gameView.getButtonY() + 80);
        boolean quit = buttonSpace(e, gameView.getButtonX(), gameView.getButtonY());
        boolean vol = buttonSpace(e, gameView.getButtonX(), gameView.getButtonY() + 120);
        boolean plusVol = buttonSpace(e, gameView.getButtonX(), gameView.getButtonY() + 160);
        boolean minVol = buttonSpace(e, gameView.getButtonX(), gameView.getButtonY() + 200);
        //if statements which indicates the functionality of each buttons
        //to unpause the game when it is pause
       if (clickPlay) {
            if (!(gameView.getIsGameRunning())) {
                gameView.setUnpause(false);
                level.start();
                gameView.setIsGameRunning(true);
                System.out.println("*Resume*");
            }
            //pause the game when running
        } else if (pausePlay) {
            if (gameView.getIsGameRunning()) {
                gameView.setIsGameRunning(false);

            } else {
                if (gameView.getIsGamePause() == false) {
                    gameView.setUnpause(true);
                    level.stop();
                    System.out.println("*Pause*");

                }
            }
            //pop-up a JFrame which ask the user if they want to quit or not
       }else if (quit) {
            JFrame notifi = new JFrame();
            notifi.setVisible(true);
            notifi.setSize(200, 100);
            notifi.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            notifi.setLocation(400, 300);
            JPanel j = new JPanel();
            JLabel quitText = new JLabel("Are you sure you want to quit?");
            JButton yes = new JButton("Yes");
            yes.addActionListener(e1 -> System.exit(0));
            JButton no = new JButton("No");
            no.addActionListener(e1 -> notifi.setVisible(false));
            j.add(quitText);
            j.add(yes);
            j.add(no);
            notifi.add(j);
        }
       //indicates if the volume button is clicked
        if (vol) {
            if (gameView.getisVolClicked()==false) {
                gameView.setVolClicked(true);
            }else{
                if (gameView.getisVolClicked()==true){
                    gameView.setVolClicked(false);
                }
            }
            //increase game volume
        }  if(gameView.getisVolClicked() == true) {
            if (plusVol) {
                musicVol.setincreaseVol();
            }
            //decrease game volume
        }if(gameView.getisVolClicked() == true) {
            if (minVol) {
                musicVol.setGameVol();
            }
        }
    }
    public boolean buttonSpace(MouseEvent e, int posX, int posY){
        //returns whether the user is clicking on the designated area of the button
        return e.getX()<=posX+30 && e.getX()>posX && e.getY()<=posY+30 && e.getY()>posY;
    }
    public void buttonUpdate(GameLevel level, GameView view, Music music){
        this.level = level;
        gameView = view;
        musicVol = music;
    }
}
