package game.itemObject;

import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import game.MainCharacter;

public class HeartDropSensor implements SensorListener {
    private HeartDrop heart;
    private MainCharacter protagonist;

    public HeartDropSensor(HeartDrop heart, MainCharacter protagonist) {
        this.heart = heart;
        this.protagonist = protagonist;
    }


    @Override
    public void beginContact(SensorEvent e) {
        if(e.getContactBody() instanceof MainCharacter){
            if(protagonist.getCurrentHealth()<=2){
                protagonist.addHealth();
                heart.destroy();
                System.out.println("Health: "+protagonist.getCurrentHealth());
            }else{
                System.out.println("[*] YOU HAVE MAX HEALTH");
            }
        }

    }

    @Override
    public void endContact(SensorEvent e) {


    }
}
