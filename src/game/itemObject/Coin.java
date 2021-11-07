package game.itemObject;
import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Coin extends StaticBody {
    //Creates the shape of the pickup coin and the image it contains
    private static final Shape coinShape = new CircleShape((0.4f));
    private static final BodyImage coinImage =
    new BodyImage("data/images/pickUps/pick_coin.gif",5);
    private static SoundClip coinSound;
    static {
        try{
            coinSound = new SoundClip("data/music/coins.wav");
        }catch (UnsupportedAudioFileException| IOException| LineUnavailableException e){
            System.out.println(e);
        }
    }

    /**
     * this class is used to create the object of coin, initialising the sound, body and image it contains.
     * @param world - pass through the Class world.
     */
    public Coin(World world) {
        super(world,coinShape);
        addImage(coinImage);
    }
    @Override
    //for every coin destroy it would play this sound
    public void destroy(){
        coinSound.play();
        super.destroy();
    }
}
