package game.projectiles;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.EnemyProperties.Boss;
import game.EnemyProperties.Enemy;
import game.EnemyProperties.Enemy2;
import game.GameLevel;
import game.itemObject.Chest;
import game.itemObject.Diamond;
import game.itemObject.HeartDrop;
import org.jbox2d.common.Vec2;

public class AttackCollision implements CollisionListener {

    private Enemy enemy;
    private Enemy2 enemy2;
    private Boss boss;

    public AttackCollision(Enemy e,Enemy2 e2,Boss boss){
        this.enemy = e;
        this.enemy2 = e2;
        this.boss = boss;
    }
    @Override
    public void collide(CollisionEvent att) {
        if(att.getOtherBody() instanceof Enemy){
            enemy.setEnemyHealth();
           if(enemy.getEnemyHealth()<=0){
                enemy.destroy();
                Chest c = new Chest(enemy.getWorld());
                c.setPosition(new Vec2(enemy.getPosition()));
            }
        }if(!(att.getReportingBody() instanceof Enemy)){
            att.getReportingBody().destroy();
        }if(att.getOtherBody() instanceof Enemy2){
            enemy2.setE2health();
            if(enemy2.getE2health() <=0){
                enemy2.destroy();
                HeartDrop h = new HeartDrop((GameLevel) enemy2.getWorld());
                h.setPosition(new Vec2(enemy2.getPosition()));
                h.setGravityScale(0);
                for(int i = 0;i<5;i++) {
                    Diamond diamond = new Diamond(enemy2.getWorld());
                    diamond.setPosition(new Vec2(enemy2.getPosition()));
                    diamond.setLinearVelocity(
                            new Vec2((float) (Math.random() * 30 - 10),
                                    (float) (Math.random() * 20)));
                }
            }
        }if(att.getOtherBody() instanceof Boss){
            boss.setBossHealth();
            if(boss.getBossHealth() <=0){
                boss.destroy();
                Chest bc = new Chest(boss.getWorld());
                bc.setPosition(new Vec2(3,0));
                for(int i = 0;i<30;i++) {
                    Diamond diamond = new Diamond(boss.getWorld());
                    diamond.setPosition(new Vec2(boss.getPosition()));
                    diamond.setLinearVelocity(
                            new Vec2((float) (Math.random() * -30),
                                    (float) (Math.random() * 20)));
                }
            }
        }
    }
}
