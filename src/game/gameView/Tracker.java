package game.gameView;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import game.MainCharacter;
import game.gameView.GameView;
import org.jbox2d.common.Vec2;
public class Tracker implements StepListener {
    private GameView view;
    private MainCharacter protagonist;
    public Tracker (GameView view,MainCharacter protagonist){
        this.view = view;
        this.protagonist = protagonist;
    }
    @Override
    public void preStep(StepEvent stepEvent) {

    }

    @Override
    public void postStep(StepEvent stepEvent) {
        //Tracks the mainCharacter's y coordinates only and not the x coordinates
        if(protagonist.getPosition().y>=-15) {
            view.setView(new Vec2(0, protagonist.getPosition().y+9), 19.5f);
        }
    }
}
