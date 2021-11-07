package game.controls;

import game.Game;
import game.GameLevel;
import game.SaverAndLoader;
import javax.swing.*;
import java.awt.*;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.util.Map;

public class Buttons extends JPanel {
    private GameLevel level;
    private Game game;


    /**
     * This class stores the buttons that is used within the game, providing functionality of saving, loading, skipping, etc.
     * @param level - Gamelevel class is passed through this constructor.
     * @param game - Game class is is passed through this constructor
     */
    public Buttons(GameLevel level, Game game){
        this.level = level;
        this.game = game;
        //here are where the buttons are being created.
        JPanel SkipLevel = new JPanel();
        JButton Forward = new JButton("Forward");
        JSeparator split = new JSeparator();
        JButton Back = new JButton("Back");
        JLabel indication = new JLabel("Skip Levels");
        JButton restart = new JButton("Restart");
        JButton saving = new JButton("Save");
        JButton loading = new JButton("Load");
        JButton info = new JButton("How to play");

        //this section is how the outlay of buttons would appear on the window
        this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
        this.add(SkipLevel);
        SkipLevel.setLayout(new BoxLayout(SkipLevel,BoxLayout.X_AXIS));
        SkipLevel.add(Back);
        SkipLevel.add(indication);
        SkipLevel.add(Forward);
        SkipLevel.add(split);
        SkipLevel.add(info);
        SkipLevel.add(saving);
        SkipLevel.add(loading);
        SkipLevel.add(restart);

        //JFrame is created for the 'How to play' button for advice for new user's of how to play the game.
        JFrame infobox = new JFrame();
        infobox.setVisible(false);
        infobox.setSize(400,150);
        infobox.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        infobox.setLocation(400, 300);
        JPanel information = new JPanel();
        JLabel space1 = new JLabel("       ");
        JLabel text = new JLabel("How To play");
        JLabel space = new JLabel("      ");
        Font font = text.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        text.setFont(font.deriveFont(attributes));
        JLabel subheading = new JLabel("The controls of this game are listed below:");
        JLabel movement = new JLabel("Movements: 'A,W,D'-> A-move left, D-move right, W-Jump.");
        JLabel special = new JLabel("Special Keys: 'SHIFT & SPACE'-> SHIFT-run, Space-attack.");
        JButton close = new JButton("Close");
        close.addActionListener(e -> infobox.setVisible(false));

        //this is where the buttons are being added to the panel
        information.add(space1);
        information.add(text);
        information.add(space);
        information.add(subheading);
        information.add(movement);
        information.add(special);
        information.add(close);
        //the panel being added to the JFrame of the game window
        infobox.add(information);

        //function of buttons, go to next level, back, restart, saving and loading
        Forward.addActionListener( e -> level.getGame().goToNextStage());
        Back.addActionListener( e -> level.getGame().goToBackLevel());
        info.addActionListener(e -> infobox.setVisible(true));
        restart.addActionListener(e -> level.getGame().restart());
        saving.addActionListener(e -> {
            try {
                SaverAndLoader.saving(game.getLevel(),"data/saving/save.txt");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            System.out.println("File saved.");
        });
        loading.addActionListener(e -> {
            try {
               GameLevel levelload = SaverAndLoader.loading(game,"data/saving/save.txt");
               game.setLevel(levelload);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
    }
}
