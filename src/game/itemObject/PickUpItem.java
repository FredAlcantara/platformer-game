package game.itemObject;
import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import game.MainCharacter;
import game.projectiles.Bullet;
import org.jbox2d.common.Vec2;

public class PickUpItem implements CollisionListener {
    private MainCharacter protagonist;

    /**
     * Objects that the main character can collide
     * with, which updates the attributes provide
     * in the mainCharacter class
     *(e.g health, coin count, etc).
     * @param p This constructor passes through p,
     *          which is the value of MainCharacter.
     */
    public PickUpItem(MainCharacter p){
        this.protagonist = p;
    }
    @Override
    public void collide(CollisionEvent item) {
        //Object coin when collided increments coin count
        //destroys the coin collect
        //finally when all coins are collected create new object in the world
        if(item.getOtherBody() instanceof Coin){
        protagonist.countCoin();
        item.getOtherBody().destroy();
        //Object heart when collided increments health
        }
        //Object diamond when collided increments diamond counter
        if(item.getOtherBody() instanceof Diamond){
            protagonist.diamondCount();
            item.getOtherBody().destroy();
        }
        if(item.getOtherBody() instanceof Chest){
            Key k = new Key(protagonist.getWorld());
            k.setPosition(item.getOtherBody().getPosition());
            new Vec2((float) (Math.random() * 30 - 10),
                    (float) (Math.random() * 30));
            for (int i=0;i<5;i++){
                Diamond d = new Diamond(protagonist.getWorld());
                d.setPosition(item.getOtherBody().getPosition());
                d.setLinearVelocity(
                        new Vec2((float) (Math.random() * 30 - 10),
                                (float) (Math.random() * 20)));
            }
            item.getOtherBody().destroy();
        }if(item.getOtherBody() instanceof Key){
            protagonist.keyCounter();
            item.getOtherBody().destroy();
        }if(item.getOtherBody() instanceof LiftPlatform){
            protagonist.move(new Vec2(0,protagonist.getPosition().y =+10));
            item.getOtherBody().destroy();
        }if (item.getOtherBody() instanceof Spiral){
            if(protagonist.getCoinCounter()>=8){
                protagonist.setPosition(new Vec2(18,6));
            }else{
                System.out.println("[*]You need 5 coins, to use the portal[*]");
            }
        }if (item.getOtherBody() instanceof Bullet){
            item.getOtherBody().destroy();
            protagonist.currentHealth();
            protagonist.setLinearVelocity(new Vec2(-10,0));
            if (protagonist.getCurrentHealth() <= 0) {
                protagonist.getPosition();
                protagonist.deathGetter();
            }
        }
    }
}
