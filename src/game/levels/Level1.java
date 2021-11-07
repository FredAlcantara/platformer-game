package game.levels;
import city.cs.engine.*;
import game.Game;
import game.GameLevel;
import game.itemObject.Coin;
import org.jbox2d.common.Vec2;

public class Level1 extends GameLevel {
    private Coin coin;

    /**
     * this is all properties of level 1, how the level is structured and designed
     * @param game - Game class, to initialises the class in game.
     */
    public Level1(Game game) {
        super(game);

        //creation of the floor
        Shape groundShape = new BoxShape(50f,1.5f);
        StaticBody ground = new StaticBody(this,groundShape);
        ground.setPosition(new Vec2(-2, -16));
        ground.addImage(new BodyImage("data/images/backgroundfloor1.png",4));
        //creation of the walls
        Shape walls = new BoxShape(0.5f,50f);
        StaticBody wallBody = new StaticBody(this, walls);
        wallBody.setPosition(new Vec2(-21,-10));
        wallBody = new StaticBody(this, walls);
        wallBody.setPosition(new Vec2(21,-10));
        //creation of platforms
        Shape platformShape2 = new BoxShape(5.5f,0.5f);
        StaticBody platform2 = new StaticBody(this, platformShape2);
        platform2.setPosition(new Vec2(0,-10f));
        platform2.addImage(new BodyImage("data/images/floatFloor.png",1.4f));
        platform2 = new StaticBody(this, platformShape2);
        platform2.setPosition(new Vec2(-13,-4));
        platform2.addImage(new BodyImage("data/images/floatFloor.png",1.4f));
        platform2 = new StaticBody(this, platformShape2);
        platform2.setPosition(new Vec2(13,-4));
        platform2.addImage(new BodyImage("data/images/floatFloor.png",1.4f));
    }

    @Override
    //a override method which is used for saving the location of objects within the level
    public void populate(Game game) {
        super.populate(game);
        getEnemy().destroy();//enemy is destroyed as not required
        getEnemy2().destroy();//enemy2 is destroyed as not required
        getBoss().destroy();//boss is destroyed as not required
        getProtagonist().setPosition(new Vec2(-18, -13));//main player is set to this location
        getExit().setPosition(new Vec2(0,-7.5f));//exit set on this location

        //creation of coins in the level
        for (int i = 0; i<6;i++){
            coin = new Coin(this);
            coin.setPosition(new Vec2(-12+(i*5),-12));
        }

        for (int i = 0; i<3;i++){
            coin = new Coin(this);
            coin.setPosition(new Vec2(-16+(i*3),-2));
        }

        for (int i = 0; i<3;i++){
            coin = new Coin(this);
            coin.setPosition(new Vec2(10+(i*3),-2));
        }
    }

    @Override
    public boolean isComplete() {
        if(getProtagonist().getCoinCounter()>=12)
            return true;
        else
            return false;
    }

    @Override
    public String getLevelName() {
        return "Level1";
    }
}
