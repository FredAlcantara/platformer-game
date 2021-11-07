package game.itemObject;

import city.cs.engine.*;
import game.GameLevel;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class HeartDrop extends DynamicBody {
    GameLevel level;
    private static final Shape heartShape = new CircleShape((float) 0.2);
    private static final BodyImage heart =
            new BodyImage("data/images/pickUps/heart.gif",2);
    private static SoundClip heartSound;
    static {
        try{
            heartSound = new SoundClip("data/music/heart.wav");
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }
    }

    /**
     * this class is used to create the object of heart, initialising the sound, body and image it contains.
     * @param level - Gamelevel class is passed through as this object will be used for every level.
     */
    public HeartDrop(GameLevel level) {
        super(level);
        this.level = level;
        //a ghostly fixture provided to prevent other objects from colliding for it.
        new GhostlyFixture(this, heartShape);
        // a sensor as the player can only have a maximum of 3 health, a sensor prevents this
        Sensor s = new Sensor(this,heartShape);
        s.addSensorListener(new HeartDropSensor(this,level.getProtagonist()));
        addImage(heart);
    }
    @Override
    // for every heart collided plays this sound.
    public void destroy() {
        heartSound.play();
        super.destroy();
    }
}
