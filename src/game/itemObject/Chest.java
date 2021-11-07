package game.itemObject;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Chest extends DynamicBody {

    private static SoundClip chestBreaking;
    static {
        try{
            chestBreaking = new SoundClip("data/music/chestopen.wav");
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            System.out.println(e);
        }
    }
    private static final PolygonShape chestShape = new PolygonShape(
            -1.28f,0.76f,
            -0.01f,1.01f,
            1.27f,0.7f,
            1.25f,-1.03f,
            -1.24f,-1.01f,
            -1.3f,0.71f
    );
    private static final BodyImage chestImage =
            new BodyImage("data/images/pickUps/chest.png",3);

    /**
     * this class is used to create the object of a chest, initialising the sound, body and image it contains.
     * @param w - the World class is passed through.
     */
    public Chest(World w) {
        super(w, chestShape);
        addImage(chestImage);
    }
    @Override
    public void destroy(){
        //for every destroyed chest, play this sound
        chestBreaking.play();
        super.destroy();
    }

}
