package game.levels;

import city.cs.engine.*;
import game.EnemyProperties.Enemy2;
import game.EnemyProperties.Thwomp;
import game.Game;
import game.GameLevel;
import game.itemObject.Box;
import game.itemObject.Coin;
import org.jbox2d.common.Vec2;

public class Level3 extends GameLevel {
    private Coin pointCoin;
    private Box box;
    private Thwomp thwomp;
    private Enemy2 enemy2;

    public Level3(Game game) {
        super(game);

        //Floor of the game
        Shape groundShape = new BoxShape(50f, 1.5f);
        StaticBody ground = new StaticBody(this, groundShape);
        ground.addImage(new BodyImage("data/images/level2Floor3.png", 3));
        ground.setPosition(new Vec2(-2, -16));

        Shape walls = new BoxShape(0.5f,50f);
        StaticBody wallBody = new StaticBody(this, walls);
        wallBody.setPosition(new Vec2(-21,-10));
        wallBody = new StaticBody(this, walls);
        wallBody.setPosition(new Vec2(21,-10));

        //1st platform (length of halfWidth 14, height of 0.5 halfHeight)
        Shape platformShape1 = new BoxShape(14, 0.5f);
        StaticBody platform1 = new StaticBody(this, platformShape1);
        platform1.addImage(new BodyImage("data/images/level3longP.png", 1.5f));
        platform1.setPosition(new Vec2(-12, -6));

        //2nd platform (length of halfWidth 5.5, height of 0.5 halfHeight)
        Shape platformShape2 = new BoxShape(5.5f, 0.5f);
        StaticBody platform2 = new StaticBody(this, platformShape2);
        platform2.addImage(new BodyImage("data/images/platformLevel3.png", 1.6f));
        platform2.setPosition(new Vec2(14, 5f));

        //3rd platform initiated (called from the shape of platform2)
        platform2 = new StaticBody(this, platformShape2);
        platform2.addImage(new BodyImage("data/images/platformLevel3.png", 1.6f));
        platform2.setPosition(new Vec2(15.5f, -9.5f));

        //4rd platform initiated (called from the shape of platform2)
        platform2 = new StaticBody(this, platformShape2);
        platform2.addImage(new BodyImage("data/images/platformLevel3.png", 1.6f));
        platform2.setPosition(new Vec2(-6, 1f));

        platform2 = new StaticBody(this,platformShape2);
        platform2.addImage(new BodyImage("data/images/platformLevel3.png",1.6f));
        platform2.setPosition(new Vec2(0, 12));

        platform2= new StaticBody(this,platformShape2);
        platform2.addImage(new BodyImage("data/images/platformLevel3.png",1.6f));
        platform2.setPosition(new Vec2(-10,22));

        Shape shapeStopper = new BoxShape(0.2f,0.2f);
        StaticBody bodyStopper = new StaticBody(this, shapeStopper);
        bodyStopper.setPosition(new Vec2(-18,-5.4f));

        bodyStopper = new StaticBody(this, shapeStopper);
        bodyStopper.setPosition(new Vec2(-4,12.6f));

        for(int i = 0; i<3; i++) {
            thwomp = new Thwomp(this);
            thwomp.setPosition(new Vec2(-12+(i*6), -10+(i+1.5f)));
        }


    }

    @Override
    public void populate(Game game) {
        super.populate(game);
        getBoss().destroy();
        getProtagonist().setPosition(new Vec2(-18, -13));
        getEnemy().setPosition(new Vec2(-14,25));
        getEnemy().startWalking(2);
        getExit().setPosition(new Vec2(18, -12.5f));


        for (int i = 0; i<3; i++){
            pointCoin =new Coin(this);
            pointCoin.setPosition(new Vec2(-14.5f+(i*6),-12));
        }

        for (int i = 0; i<4; i++){
            pointCoin = new Coin(this);
            pointCoin.setPosition(new Vec2(10+(i*3),-7));
        }

        for (int i = 0; i<3; i++){
            pointCoin = new Coin(this);
            pointCoin.setPosition(new Vec2(-9+(i*3),3));
        }

        box = new Box(this);
        box.setPosition(new Vec2(-1,-4));
        box = new Box(this);
        box.setPosition(new Vec2(4,13f));


        getEnemy2().setPosition(new Vec2(17,6));
        getEnemy2().startWalking(-2);


    }

    @Override
    public boolean isComplete() {
        if (getProtagonist().getKey() >= 1 && (getProtagonist().getCoinCounter()>=5))
            return true;
        else
            return false;
    }
    @Override
    public String getLevelName() {
        return "Level3";
    }
}

