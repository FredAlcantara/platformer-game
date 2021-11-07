package game.itemObject;

import city.cs.engine.*;
import game.GameLevel;
import game.levels.FinalLevel;
import game.levels.Level4;

public class MovingPlatform extends Walker{

    private static Shape movingShape = new BoxShape(2,0.5f);
    private BodyImage image = new BodyImage("data/images/movingplat.png");
    private float move = 0.2f;

    public MovingPlatform(GameLevel w) {
        super(w,movingShape);
        addImage(image);
        startWalking(6);
        w.addStepListener(new StepListener() {
            @Override
            public void preStep(StepEvent stepEvent) {
            }

            @Override
            public void postStep(StepEvent stepEvent) {
                if(w instanceof Level4) {
                    if (Math.rint(getPosition().x) == -6) {
                        stopWalking();
                        startWalking(6);
                    } else if (Math.rint(getPosition().x) == 8) {
                        stopWalking();
                        startWalking(-6);
                    }
                }else if (w instanceof FinalLevel){
                    if(Math.rint(getPosition().x)==13){
                        stopWalking();
                        startWalking(-6);
                    }else if (Math.rint(getPosition().x) == 1) {
                        stopWalking();
                        startWalking(6);
                    }
                }
            }
        });
    }
}
