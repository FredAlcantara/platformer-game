package game.itemObject;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Diamond extends DynamicBody {
    //Creates the shape of the pickup diamond and the image it contains
    private static final Shape diamondShape = new CircleShape((float) 0.2);
    private static final BodyImage diamond =
            new BodyImage("data/images/pickUps/diamond.gif",2);

    private static SoundClip diamondSound;
    static {
        try{
            diamondSound = new SoundClip("data/music/diamond.wav");
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }
    }
    public Diamond(World w) {
        super(w,diamondShape);
        addImage(diamond);
        //setPosition(new Vec2((float) (Math.random() * 20-10),
               //(float) (Math.random() * 15)));
    }
    @Override
    public void destroy() {
        diamondSound.play();
        super.destroy();
    }

}
