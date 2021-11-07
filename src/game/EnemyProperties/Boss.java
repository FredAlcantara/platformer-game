package game.EnemyProperties;

import city.cs.engine.*;
import game.GameLevel;
import org.jbox2d.common.Vec2;

import javax.swing.*;

public class Boss extends StaticBody implements StepListener{
    private static final Shape Bossshape = new BoxShape(0.3f,4f);
    private BodyImage BossImage = new BodyImage("data/images/Boss.gif",8);
    private float move = 0.2f;
    private int BossHealth;

    public Boss(GameLevel w) {
        super(w,Bossshape);
        addImage(BossImage);
        Timer timer = new Timer(2000,e ->{ move*=-1; });
        timer.start();
        BossHealth = 10;
        getWorld().addStepListener(this);

    }
    public void setBossHealth() {
        BossHealth--;

    }
    public void setBossHealthload(int health){
        BossHealth = health;
    }

    public int getBossHealth(){
        return BossHealth;
    }

    @Override
    public void preStep(StepEvent stepEvent) {
        setPosition((getPosition().add(new Vec2(0,move))));

    }

    @Override
    public void postStep(StepEvent stepEvent) {
    }
}
