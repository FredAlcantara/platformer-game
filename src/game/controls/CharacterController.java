package game.controls;
import city.cs.engine.BodyImage;
import game.EnemyProperties.Boss;
import game.projectiles.Attack;
import game.projectiles.AttackCollision;
import game.EnemyProperties.Enemy;
import game.EnemyProperties.Enemy2;
import game.MainCharacter;
import game.SFX.SoundEffects;
import org.jbox2d.common.Vec2;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class CharacterController implements KeyListener {

    private static float movement = 5;
    private static float jumping = 12.5f;
    private MainCharacter protagonist;
    private Enemy enemy;
    private Enemy2 enemy2;
    private Boss boss;
    private SoundEffects soundEffects= new SoundEffects();


    /**
     * CharacterController class used to assign
     * the controls for the character, for example, movement, jumping and attacking.
     * @param p - parameter which passed through the MainCharacter, accessing
     *          CharacterController class to be called in the GameWorld.
     * @param e - this passes in the Enemy class as it is used to add collision when the player attacks them.
     * @param b - passes the Boss class for collision.
     * @param e2 - passes Enemy2 to add collision
     */
    public CharacterController (MainCharacter p, Enemy e,Enemy2 e2,Boss b){
        protagonist = p;
        enemy = e;
        enemy2 = e2;
        boss = b;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    //Key event D sets the character to move left and change image
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // other key commands omitted
        if (code == KeyEvent.VK_D) {
            protagonist.setIsRight(true);
            protagonist.startWalking(movement);
            setProtagonistImage("data/images/characterRunning.gif", protagonist.getisRight());
            //Key event D sets the character to move left and change image
        } else if (code == KeyEvent.VK_A) {
            protagonist.setIsRight(false);
            protagonist.startWalking(-movement);
            setProtagonistImage("data/images/characterRunning.gif", protagonist.getisRight());
            //Key event W sets the character to move left and change image
        } else if (code == KeyEvent.VK_W) {
            protagonist.jump(jumping);
            soundEffects.soundeffectPlays("jumping");
            protagonist.stopWalking();
            protagonist.removeAllImages();
            protagonist.addImage(new BodyImage("data/images/characterJump.gif", 7));
            if (!protagonist.getisRight()){
                protagonist.getImages().get(0).flipHorizontal();
            }
            //Key event Shift which increases movement speed
             }else if (code == KeyEvent.VK_SHIFT){
            movement = movement*3;
             }else if(code == KeyEvent.VK_SPACE){
            protagonist.removeAllImages();
            protagonist.addImage(new BodyImage("data/images/characterAttack.gif", 7 ));
            soundEffects.soundeffectPlays("attacking");
                //when user presses the space bar it produces this attack object.
                Attack att = new Attack(protagonist.getWorld());
                att.setPosition(protagonist.getPosition().add(new Vec2(2,0)));
                //this object is applied to certain object to contain the collision with this object
                AttackCollision enemyLoseHealth = new AttackCollision(enemy,enemy2,boss);
                att.addCollisionListener(enemyLoseHealth);
                //checks whether the character is facing right when the attack object is called.
                //if so apply the right direction of impulse (left or right) and set the attack object in the right direction.
            if (!protagonist.getisRight()){
                att.setPosition(protagonist.getPosition().add(new Vec2(-2,0)));
                att.applyImpulse(new Vec2(-10,0));
                protagonist.getImages().get(0).flipHorizontal();
            }else{
                att.applyImpulse(new Vec2(10,0));
                att.getImages().get(0).flipHorizontal();
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //when key D is released stop walking and set the image to the stationary image
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_D) {
            protagonist.stopWalking();
            protagonist.setLinearVelocity(new Vec2(0,0));
            protagonist.removeAllImages();
            protagonist.addImage(new BodyImage("data/images/characterStationary.gif",5));
         //when key A is released stop walking and set the image to the stationary image
        } else if (code == KeyEvent.VK_A) {
            protagonist.stopWalking();
            protagonist.setLinearVelocity(new Vec2(0,0));
            protagonist.removeAllImages();
            protagonist.addImage(new BodyImage("data/images/characterStationary.gif",5)).flipHorizontal();
        }else if (code == KeyEvent.VK_W) {
            protagonist.removeAllImages();
            protagonist.addImage(new BodyImage("data/images/characterStationary.gif",5));
            if (!protagonist.getisRight()){
                protagonist.getImages().get(0).flipHorizontal();
            }
            //upon release, initialises movement back to original value
        }else if (code == KeyEvent.VK_SHIFT){
            movement = 5;
        }else if (code == KeyEvent.VK_SPACE){
            protagonist.removeAllImages();
            protagonist.addImage(new BodyImage("data/images/characterStationary.gif",5));
            if (!protagonist.getisRight()) {
                protagonist.getImages().get(0).flipHorizontal();
            }
        }
    }

    /**
     * A method that sets the main character stationary image and flips it, if not facing right.
     * @param imagePath - A String for passing through the stationary image.
     * @param isRight - validates if the main character is facing right.
     */
    public void setProtagonistImage(String imagePath, boolean isRight) {
        protagonist.removeAllImages();
        BodyImage newImage = new BodyImage(imagePath, 6);
        protagonist.addImage(newImage);
        if (!isRight) {
            protagonist.getImages().get(0).flipHorizontal();
        }
    }

    /**
     * A method which updates the controller in different levels.
     * @param protagonist - Maincharacter class is passed through.
     * @param enemy - Enemy class is passed through.
     * @param enemy2 - Enemy2 class is passed through.
     * @param boss - Boss class is passed through.
     */
    public void updateControls(MainCharacter protagonist,Enemy enemy, Enemy2 enemy2,Boss boss){
        this.protagonist = protagonist;
        this.enemy = enemy;
        this.enemy2 = enemy2;
        this.boss =boss;
    }
}
