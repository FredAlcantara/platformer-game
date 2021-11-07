package game.levels;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import game.EnemyProperties.Spike;
import game.EnemyProperties.Thwomp;
import game.Game;
import game.GameLevel;
import game.itemObject.MovingPlatform;
import game.itemObject.Spiral;
import game.itemObject.Coin;
import game.itemObject.HeartDrop;
import game.itemObject.InfoBlock;
import game.itemObject.LiftPlatform;
import org.jbox2d.common.Vec2;

public class Level4 extends GameLevel {

    private MovingPlatform movingPlatform;
    private Spike spike;
    private Thwomp thwomp;
    private LiftPlatform liftPlatform;
    private InfoBlock infoBlock;
    private Coin coin;
    private HeartDrop heartDrop;
    private Spiral spiral;

    public Level4(Game game) {
        super(game);


        movingPlatform = new MovingPlatform(this);
        movingPlatform.setPosition(new Vec2(0,-15));

        spike = new Spike(this);
        spike.setPosition(new Vec2(0.1f,-16));

        //Floor of the game
      /*  Shape groundShape = new BoxShape(50f, 1.5f);
        StaticBody ground = new StaticBody(this, groundShape);
        ground.addImage(new BodyImage("data/images/metalicTile.png", 5));
        ground.setPosition(new Vec2(-2, -16));*/

        Shape groundShape2 = new BoxShape(10f, 1.5f);
        StaticBody ground2 = new StaticBody(this, groundShape2);
        ground2.setPosition(new Vec2(-18, -16));
        ground2.addImage(new BodyImage("data/images/brickLevel4.png",4));

        ground2 = new StaticBody(this, groundShape2);
        ground2.setPosition(new Vec2(20.2f, -16));
        ground2.addImage(new BodyImage("data/images/brickLevel4.png",4)).flipHorizontal();

        Shape walls = new BoxShape(0.5f,50f);
        StaticBody wallBody = new StaticBody(this, walls);
        wallBody.setPosition(new Vec2(-21,-10));
        wallBody = new StaticBody(this, walls);
        wallBody.setPosition(new Vec2(21,-10));

        Shape groundShape = new BoxShape(30f, 0.5f);
        StaticBody ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(-2, -17));
        ground.addImage(new BodyImage("data/images/spikeBase.png",1));

        Shape platformShape1 = new BoxShape(23, 0.8f);
        StaticBody platform1 = new StaticBody(this, platformShape1);
        platform1.setPosition(new Vec2(0, -8));
        platform1.addImage(new BodyImage("data/images/platformlevel4.png"));

        platform1 = new StaticBody(this, platformShape1);
        platform1.setPosition(new Vec2(0, 2));
        platform1.addImage(new BodyImage("data/images/platformlevel4.png"));

        platform1 = new StaticBody(this, platformShape1);
        platform1.setPosition(new Vec2(-0, 12));
        platform1.addImage(new BodyImage("data/images/platformlevel4.png"));

        platform1 = new StaticBody(this, platformShape1);
        platform1.setPosition(new Vec2(-0, 22));
        platform1.addImage(new BodyImage("data/images/platformlevel4.png"));

        infoBlock = new InfoBlock(this);
        infoBlock.setPosition(new Vec2(18,-4));
        infoBlock = new InfoBlock(this);
        infoBlock.setPosition(new Vec2(-18,6));
        infoBlock = new InfoBlock(this);
        infoBlock.setPosition(new Vec2(18,16));

        for(int i =0; i<5;i++){
            thwomp = new Thwomp(this);
            thwomp.setPosition(new Vec2(-12+(i*6),-6));
        }
        for(int i =0; i<2;i++){
            thwomp = new Thwomp(this);
            thwomp.setPosition(new Vec2(-10+(i*6),5));
        }
        for(int i =0; i<2;i++){
            thwomp = new Thwomp(this);
            thwomp.setPosition(new Vec2(4+(i*6),5));
        }

        spiral = new Spiral(this);
        spiral.setPosition(new Vec2(18,25));

    }

    @Override
    public void populate(Game game) {
        super.populate(game);

        getProtagonist().setPosition(new Vec2(-18, -13));
        getEnemy().setPosition(new Vec2(0,23));
        getEnemy().startWalking(2);
        getEnemy2().setPosition(new Vec2(0,22));
        getEnemy2().startWalking(-2);
        getExit().setPosition(new Vec2(0, 4.5f));
        getBoss().destroy();

        liftPlatform = new LiftPlatform(this);
        liftPlatform.setPosition(new Vec2(18,-12));
        liftPlatform = new LiftPlatform(this);
        liftPlatform.setPosition(new Vec2(-18,-4));
        liftPlatform = new LiftPlatform(this);
        liftPlatform.setPosition(new Vec2(18,6));
        liftPlatform = new LiftPlatform(this);
        liftPlatform.setPosition(new Vec2(-18,16));

        for(int i = 0; i<6;i++){
            coin = new Coin(this);
            coin.setPosition(new Vec2(-6+(3*i),-12));
        }

        for(int i = 0; i<4;i++){
            coin = new Coin(this);
            coin.setPosition(new Vec2(-9+(6*i),-4));
        }

        heartDrop = new HeartDrop(this);
        heartDrop.setPosition(new Vec2(14,-12));
        heartDrop.setGravityScale(0);

        heartDrop = new HeartDrop(this);
        heartDrop.setPosition(new Vec2(0,16));
        heartDrop.setGravityScale(0);

    }

    @Override
    public boolean isComplete() {
        if (getProtagonist().getKey() >= 1 &&(getProtagonist().getCoinCounter()>=9))
            return true;
        else
        return false;
    }
    @Override
    public String getLevelName() {
        return "Level4";
    }
}
