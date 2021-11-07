package game.SFX;

import city.cs.engine.SoundClip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.HashMap;

public class SoundEffects {
    private HashMap<String, SoundClip> effects;
    private SoundClip jumping, attacking;

    /**
     * this is a class where all charater sound effects has be initialised such as jumping and attacking
     */
    public SoundEffects(){
        try{
            effects = new HashMap<>();
            jumping = new SoundClip("data/music/jumping.wav");
            attacking = new SoundClip("data/music/sword.wav");
        }catch (IOException | LineUnavailableException | UnsupportedAudioFileException e){
            System.out.println(e);
        }
        effects.put("jumping",jumping);
        effects.put("attacking",attacking);
    }
    public void soundeffectPlays(String key){
        effects.get(key).play();
    }
}
