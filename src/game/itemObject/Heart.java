package game.itemObject;
import city.cs.engine.*;
import game.Game;
import game.GameLevel;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Heart extends StaticBody {
    private GameLevel level;
    //Creates the shape of the pickup heart and the image it contains
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

    public Heart(GameLevel level) {
        super(level);
        this.level = level;
        new GhostlyFixture(this, heartShape);
        Sensor s = new Sensor(this,heartShape);
        s.addSensorListener(new HeartSensor(this, level.getProtagonist()));
        addImage(heart);
    }
    @Override
    public void destroy() {
        heartSound.play();
        super.destroy();
    }
}
