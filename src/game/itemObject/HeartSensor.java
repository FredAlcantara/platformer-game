package game.itemObject;

import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import game.MainCharacter;

public class HeartSensor implements SensorListener {
    private Heart heart;
    private MainCharacter protagonist;

    /**
     * this is the sensor for the health of the main character.
     * @param heart - Heart class is passed through as it is the object that will be referred to.
     * @param protagonist  - MainCharacter class to obtain the getter method of the hearts.
     */
    public HeartSensor(Heart heart,MainCharacter protagonist) {
        this.heart = heart;
        this.protagonist = protagonist;
    }

    @Override
    public void beginContact(SensorEvent e) {
        //if players heart is equal 2 or less, increment.
        if(e.getContactBody() instanceof MainCharacter){
            if(protagonist.getCurrentHealth()<=2){
                protagonist.addHealth();
                heart.destroy();
                System.out.println("Health: "+protagonist.getCurrentHealth());
            }else{
                //else show this message in the console.
                System.out.println("[*] YOU HAVE MAX HEALTH");
            }
        }

    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }
}
