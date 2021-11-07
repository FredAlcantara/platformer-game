package game.EnemyProperties;

import city.cs.engine.*;

public class Thwomp extends Walker {

    private static final Shape thwompShape = new BoxShape(0.2f,0.2f);
    private BodyImage thwompImage =
            new BodyImage("data/images/Thwomp.png",3);


    public Thwomp(World world) {
        super(world,thwompShape);//the object is initialised in the world
        addImage(thwompImage);//image has been applied
        world.addStepListener(new StepListener() {
            @Override
            public void preStep(StepEvent stepEvent) {

            }

            @Override
            public void postStep(StepEvent stepEvent) {
                jump(12);//for every frame make the object jump
            }
        });
    }
}
