package game.itemObject;
import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import game.GameLevel;
import game.MainCharacter;
import game.levels.Level1;
import game.levels.Level2;
import game.levels.Level3;
import game.levels.Level4;


public class ExitSensor implements SensorListener {
    private Exit exit;
    private GameLevel level;

    public ExitSensor(Exit exit,GameLevel level){
        this.exit = exit;
        this.level = level;
    }
    @Override
    public void beginContact(SensorEvent e) {
        if(e.getContactBody() instanceof MainCharacter){
            if(exit.getLevel().isComplete()){
                exit.getLevel().getGame().goToNextStage();
            }else if (level instanceof Level2) {
                    System.out.println("Kill the enemy to obtain a key, to ENTER");
                }if(level instanceof Level3){
                System.out.println("Collect at least 5 coins and obtain a key to Enter");
            }if(level instanceof Level4){
                System.out.println("Collect all coins in the level and find the key");
            }if(level instanceof Level1){
                System.out.println("Collect all coins to Enter");
            }
        }
    }
    @Override
    public void endContact(SensorEvent e) {

    }
}
