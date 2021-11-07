package game.levels;
import city.cs.engine.*;
import city.cs.engine.Shape;
import game.*;
import game.EnemyProperties.Spike;
import game.EnemyProperties.Thwomp;
import game.itemObject.*;
import game.projectiles.Bullet;
import game.projectiles.BulletCollision;
import org.jbox2d.common.Vec2;

import javax.swing.*;
import java.awt.*;

public class FinalLevel extends GameLevel {

    private Coin coin;
    private HeartDrop heartDrop;
    private Chest chest;
    private Thwomp thwomp;
    private LiftPlatform liftPlatform;
    private Spike spike;
    private MovingPlatform movingPlatform;
    private Bullet bullet;
    private Timer timer = new Timer(1500, e ->bullet= new Bullet(this));
    public FinalLevel(Game game) {
        super(game);


        Shape groundShape = new BoxShape(20f,1.5f);
        StaticBody ground = new StaticBody(this,groundShape);
        ground.setPosition(new Vec2(-10, -16));
        ground.addImage(new BodyImage("data/images/lavaPlatform.png",3));

        Shape walls = new BoxShape(0.5f,50f);
        StaticBody wallBody = new StaticBody(this, walls);
        wallBody.setPosition(new Vec2(21,-10));

        Shape platform = new BoxShape(3,0.1f);
        StaticBody platformShape = new StaticBody(this, platform);
        platformShape.setPosition(new Vec2(-10,-9));
        platformShape.setFillColor(Color.black);

        platformShape = new StaticBody(this,platform);
        platformShape.setPosition(new Vec2(3,-3));
        platformShape.setFillColor(Color.black);

        platformShape = new StaticBody(this,platform);
        platformShape.setPosition(new Vec2(18,-6));
        platformShape.setFillColor(Color.black);

        platformShape = new StaticBody(this,platform);
        platformShape.setPosition(new Vec2(-18,26));
        platformShape.setFillColor(Color.black);

        platformShape = new StaticBody(this,platform);
        platformShape.setPosition(new Vec2(-12,32));
        platformShape.setFillColor(Color.black);

        platformShape = new StaticBody(this,platform);
        platformShape.setPosition(new Vec2(-20,38));
        platformShape.setFillColor(Color.black);

        Shape topPlatform = new BoxShape(10,0.1f);
        StaticBody topPlatformBody = new StaticBody(this, topPlatform);
        topPlatformBody.setPosition(new Vec2(-11,3));
        platformShape.setFillColor(Color.black);
        topPlatformBody.setFillColor(Color.black);

        topPlatformBody = new StaticBody(this, topPlatform);
        topPlatformBody.setPosition(new Vec2(12,10));

        Shape upperLevel = new BoxShape(22,0.1f);
        StaticBody upperLevelBody = new StaticBody(this,upperLevel);
        upperLevelBody.setPosition(new Vec2(13,20));

        Shape blocks = new BoxShape(3,0.7f);
        StaticBody blockBody = new StaticBody(this, blocks);
        blockBody.setPosition(new Vec2(18,20.5f));

        Shape smallBlock = new BoxShape(2.5f,0.7f);
        StaticBody smallBlockShape = new StaticBody(this,smallBlock);
        smallBlockShape.setPosition(new Vec2(-6.5f,20.7f));

        upperLevelBody.setFillColor(Color.black);
        platformShape.setFillColor(Color.black);
        topPlatformBody.setFillColor(Color.black);
        blockBody.setFillColor(Color.black);
        smallBlockShape.setFillColor(Color.black);

        movingPlatform = new MovingPlatform(this);
        movingPlatform.setPosition(new Vec2(10,21));
        movingPlatform.startWalking(6);

        spike = new Spike(this);
        spike.setPosition(new Vec2(6,21));
        liftPlatform= new LiftPlatform(this);
        liftPlatform.setPosition(new Vec2(19,12));

        for (int i = 0; i<3;i++){
            thwomp = new Thwomp(this);
            thwomp.setPosition(new Vec2(6+(i*5),11));
        }

        timer.start();
    }

    @Override
    public void populate(Game game) {
        super.populate(game);
        getEnemy().setPosition(new Vec2(-10,8));
        getEnemy().startWalking(2);
        getEnemy2().setPosition(new Vec2(3,-13));
        getEnemy2().startWalking(-2);
        getProtagonist().setPosition(new Vec2(-18, -13));
        getExit().setPosition(new Vec2(8,-12.5f));
        getBoss().setPosition(new Vec2(18,33));
        BulletCollision collision = new BulletCollision();
        for(int i = 0; i<3;i++){
            coin = new Coin(this);
            coin.setPosition(new Vec2(-12+(i*2),-7));
        }
        heartDrop = new HeartDrop(this);
        heartDrop.setPosition(new Vec2(3,-1));
        heartDrop.setGravityScale(0);

        chest = new Chest(this);
        chest.setPosition(new Vec2(18,4));

        getEnemy().addCollisionListener(collision);
        getEnemy2().addCollisionListener(collision);
        coin.addCollisionListener(collision);

        heartDrop = new HeartDrop(this);
        heartDrop.setPosition(new Vec2(-18,28));
        heartDrop.setGravityScale(0);

        heartDrop = new HeartDrop(this);
        heartDrop.setPosition(new Vec2(-12,34));
        heartDrop.setGravityScale(0);

        heartDrop = new HeartDrop(this);
        heartDrop.setPosition(new Vec2(-18,40));
        heartDrop.setGravityScale(0);





    }

    @Override
    public boolean isComplete() {
        if(getProtagonist().getKey() == 3)
            return true;
        else
            return false;
    }
    public String getLevelName() {
        return "FinalLevel";
    }
}
