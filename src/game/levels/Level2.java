package game.levels;
import city.cs.engine.*;
import city.cs.engine.Shape;
import game.Game;
import game.GameLevel;
import game.itemObject.Coin;
import game.itemObject.Heart;
import game.itemObject.HeartDrop;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

public class Level2 extends GameLevel {

    /**
     * GameWorld Class is the section where the bodies and
     * objects are initiated. Platforms and objects uses
     * <code>StaticBody</code> to ensure no physics has been
     * applied towards to created body.
     */

    private Coin pointCoin;
    private HeartDrop addHeart;

    public Level2(Game game){
        super(game);

        //Floor of the game
        Shape groundShape = new BoxShape(50f,1.5f);
        StaticBody ground = new StaticBody(this,groundShape);
        ground.addImage(new BodyImage("data/images/metalicTile.png",5));
        ground.setPosition(new Vec2(-2, -16));

        Shape walls = new BoxShape(0.5f,50f);
        StaticBody wallBody = new StaticBody(this, walls);
        wallBody.setPosition(new Vec2(-21,-10));
        wallBody = new StaticBody(this, walls);
        wallBody.setPosition(new Vec2(21,-10));

        //1st platform (length of halfWidth 14, height of 0.5 halfHeight)
        Shape platformShape1 = new BoxShape(14,0.5f);
        StaticBody platform1 = new StaticBody(this, platformShape1);
        platform1.addImage(new BodyImage("data/images/solidplatform.png",6));
        platform1.setPosition(new Vec2(-18,0));

        //2nd platform (length of halfWidth 5.5, height of 0.5 halfHeight)
        Shape platformShape2 = new BoxShape(5.5f,0.5f);
        StaticBody platform2 = new StaticBody(this, platformShape2);
        platform2.addImage(new BodyImage("data/images/platform2.png",1.2f));
        platform2.setPosition(new Vec2(-4,-10f));

        //3rd platform initiated (called from the shape of platform2)
        StaticBody platform3 = new StaticBody(this, platformShape2);
        platform3.addImage(new BodyImage("data/images/platform2.png",1.2f));
        platform3.setPosition(new Vec2(14.5f,-3.5f));

        //4rd platform initiated (called from the shape of platform2)
        StaticBody platform4 = new StaticBody(this,platformShape2);
        platform4.addImage(new BodyImage("data/images/platform2.png",1.2f));
        platform4.setPosition(new Vec2(9,7));
    }

    @Override
    public void populate(Game game) {
        super.populate(game);
        getProtagonist().setPosition(new Vec2(-18, -13));
        //Enemy initialised in the to the world and initiated to start walking
        getEnemy().setPosition(new Vec2(-16,2));
        getEnemy().startWalking(2);
        getEnemy2().destroy();
        getBoss().destroy();
        getExit().setPosition(new Vec2(0,-12.5f));

        //pickups(coins), for loop for initializing coins to world and x position
        for(int i = 0;i<5;i++){
            pointCoin = new Coin(this);
            pointCoin.setPosition(new Vec2(6+(i*3),-12));
        }
        for(int i = 0;i<3;i++){
            pointCoin = new Coin(this);
            pointCoin.setPosition(new Vec2(-7+(i*3),-7));
        }
        for(int i = 0;i<3;i++){
            pointCoin = new Coin(this);
            pointCoin.setPosition(new Vec2(11+(i*3),-1));
        }
        //picksups(heart)
        addHeart = new HeartDrop(this);
        addHeart.setPosition(new Vec2(9,9));
        addHeart.setGravityScale(0);
    }


    @Override
    public boolean isComplete() {
        if (getProtagonist().getKey() >= 1)
            return true;
        else
            return false;
    }
    @Override
    public String getLevelName() {
        return "Level2";
    }
}
