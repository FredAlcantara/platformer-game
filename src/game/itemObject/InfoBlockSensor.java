package game.itemObject;

import city.cs.engine.SensorEvent;
import city.cs.engine.SensorListener;
import game.MainCharacter;

public class InfoBlockSensor implements SensorListener {
    private InfoBlock infoBlock;
    public InfoBlockSensor(InfoBlock infoBlock){
        this.infoBlock = infoBlock;
    }
    @Override
    public void beginContact(SensorEvent e) {
        if (e.getContactBody() instanceof MainCharacter){
            System.out.println("[*] You have teleported, no turning back now[*]");
        }
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }
}
