package game.SFX;

import city.cs.engine.SoundClip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.ArrayList;

public class Music extends Thread{
    private static ArrayList<SoundClip> gameMusic = new ArrayList<>();
    private double volume = 1;
    private double increase = 0.12;
    private double decrease = 0.12;

    /**
     * This is a class where a background music has been initialised and provided their properties such as volume.
     */

    public Music(){
        try {
            gameMusic.add(new SoundClip("data/music/level1.wav"));
            gameMusic.add(new SoundClip("data/music/music1.wav"));
            gameMusic.add(new SoundClip("data/music/Level2music.wav"));
            gameMusic.add(new SoundClip("data/music/background.wav"));
            gameMusic.add(new SoundClip("data/music/bossLevel.wav"));
            gameMusic.get(0).setVolume(volume);
            gameMusic.get(1).setVolume(volume);
            gameMusic.get(2).setVolume(volume);
            gameMusic.get(3).setVolume(volume);
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }
    public ArrayList<SoundClip> getGameMusic (){
       return gameMusic;
    }

    public double getVol(){
        return volume;
    }
    //a method of getter and setter to be used in for the buttons enabling the volume function to activate
    public void setGameVol(){
        gameMusic.get(0).setVolume(volume = volume-decrease);
        gameMusic.get(1).setVolume(volume = volume-decrease);
        gameMusic.get(2).setVolume(volume = volume-decrease);
        gameMusic.get(3).setVolume(volume = volume-decrease);
        if (volume == 0.040000000000000036){
            decrease = 0;
        }else if (volume > 0.040000000000000036){
            decrease =0.12;
        }
    }
    public void setincreaseVol(){
        gameMusic.get(0).setVolume(volume = volume+increase);
        gameMusic.get(1).setVolume(volume = volume+increase);
        gameMusic.get(2).setVolume(volume = volume+increase);
        gameMusic.get(3).setVolume(volume = volume+increase);
        if(volume == 1.9600000000000009){
            increase = 0;
        }else if(volume < 1.9600000000000009){
            increase = 0.12;
        }
    }
}
