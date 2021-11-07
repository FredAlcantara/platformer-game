package game;
import city.cs.engine.DynamicBody;
import city.cs.engine.StaticBody;
import game.EnemyProperties.Boss;
import game.EnemyProperties.Enemy;
import game.EnemyProperties.Enemy2;
import game.EnemyProperties.EnemyCollider;
import game.itemObject.*;
import game.levels.*;
import org.jbox2d.common.Vec2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SaverAndLoader {
    /**
     * this class is for enabling the user to save and load their progression in the game
     * @param level - passed in Gamelevel for indicating which level the user is in
     * @param fileName - String variable which writes the attributes of the object
     * @throws IOException - if an error is found, throw an error message.
     */
    public static void saving(GameLevel level,String fileName)
    throws IOException {

        boolean append = false;
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, append);
            writer.write(level.getLevelName() + "\n");
            //for all static bodies write down the name and position (attributes)
            for (StaticBody body : level.getStaticBodies()){
                if(body instanceof Coin){
                    writer.write("Coin,"+body.getPosition().x + "," + body.getPosition().y +"\n");
                }else if (body instanceof Exit){
                    writer.write("Exit,"+body.getPosition().x + "," + body.getPosition().y +"\n");
                }else if(body instanceof LiftPlatform){
                    writer.write("Lift, "+body.getPosition().x + "," + body.getPosition().y +"\n");
                }else if(body instanceof Boss){
                    writer.write("Boss"+"\n");
                }
            }
            //for every dynamic bodies write all the attributes
            for (DynamicBody body : level.getDynamicBodies()){
                if(body instanceof MainCharacter){
                    //writing the name, position, score, coin, key of the character
                    writer.write("Protagonist, "+body.getPosition().x + "," + body.getPosition().y +
                            "," + ((MainCharacter) body).getCurrentHealth() + "," +
                            ((MainCharacter) body).getCoinCounter()+ "," +
                            ((MainCharacter) body).getScore()+ "," + ((MainCharacter) body).getKey()+"\n");
                }else if(body instanceof Enemy){
                    writer.write("Enemy, "+body.getPosition().x + "," + body.getPosition().y+"\n");
                }else if (body instanceof Enemy2){
                    writer.write("Enemy2, "+body.getPosition().x + "," + body.getPosition().y+"\n");
                }else if (body instanceof HeartDrop){
                    writer.write("Heart,"+body.getPosition().x + "," + body.getPosition().y +"\n");
                }else if (body instanceof Box){
                    writer.write("Box,"+body.getPosition().x + "," + body.getPosition().y +"\n");
                }else if (body instanceof Chest){
                    writer.write("Chest,"+body.getPosition().x + "," + body.getPosition().y +"\n");
                }
            }
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    /**
     * this is for loading the game, retrieving the write txt file
     * which was created in the save functionality
     * @param game - passes through Gamelevel
     * @param fileName - passes the String variable of fileName
     * @return - return the read file which has been saved
     * @throws IOException - any error, throw error message
     */
    public static GameLevel loading(Game game, String fileName)
    throws IOException{
        FileReader fr = null;
        BufferedReader reader = null;
        try {
            System.out.println("Reading " + fileName + " ...");
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();

            GameLevel level = null;
            //if the string matches in the file load the specific level
            if(line.equals("Level1"))
                level = new Level1(game);
            else if(line.equals("Level2")){
                level = new Level2(game);
            }else if (line.equals("Level3")){
                level = new Level3(game);
            }else if (line.equals("Level4")){
                level = new Level4(game);
            }else if (line.equals("FinalLevel")){
                level = new FinalLevel(game);
            }

            line = reader.readLine();
            while (line != null){

                String[] tokens = line.split(",");
                //load the coins in the level
                if(tokens[0].equals("Coin")){
                    Coin c = new Coin(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    c.setPosition(new Vec2(x,y));
                //load the platform
                }else if(tokens[0].equals("Lift")){
                    LiftPlatform liftPlatform = new LiftPlatform(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    liftPlatform.setPosition(new Vec2(x,y));
                    //load the hearts
                } else if(tokens[0].equals("Heart")){
                    HeartDrop h = new HeartDrop(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    h.setPosition(new Vec2(x,y));
                    h.setGravityScale(0);
                    //load the box
                }else if(tokens[0].equals("Box")){
                    Box b = new Box(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    b.setPosition(new Vec2(x,y));
                    //load the door
                } else if(tokens[0].equals("Exit")) {
                    Exit e = new Exit(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    e.setPosition(new Vec2(x, y));
                    level.setExit(e);
                    //load the chest
                } else if(tokens[0].equals("Chest")){
                    Chest c = new Chest(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    c.setPosition(new Vec2(x,y));
                    //load the main character with the collision listenser implemented
                }else if(tokens[0].equals("Protagonist")){
                    MainCharacter p = new MainCharacter(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    p.setPosition(new Vec2(x,y));
                    int h = Integer.parseInt(tokens[3]);
                    p.setHeart(h);
                    int coin = Integer.parseInt(tokens[4]);
                    p.setCoinCounter(coin);
                    int s = Integer.parseInt(tokens[5]);
                    p.setScore(s);
                    int k = Integer.parseInt(tokens[6]);
                    p.setKey(k);
                    level.setProtagonist(p);

                    ExitEncounter encounter = new ExitEncounter(level);
                    HeartEncounter heartEncounter = new HeartEncounter(p);
                    p.addCollisionListener(encounter);
                    p.addCollisionListener(heartEncounter);
                    PickUpItem items = new PickUpItem(p);
                    p.addCollisionListener(items);
                    //EnemyCollider class called to past through main character
                    EnemyCollider loseHealth = new EnemyCollider(p);
                    p.addCollisionListener(loseHealth);
                    //load the enemy, the position and walking
                }else if(tokens[0].equals("Enemy")){
                    Enemy e = new Enemy(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    e.setPosition(new Vec2(x,y));
                    level.setEnemy(e);
                    e.startWalking(2);
                    //load enemy2, the position and walking speed
                }else if(tokens[0].equals("Enemy2")){
                    Enemy2 e2 = new Enemy2(level);
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    e2.setPosition(new Vec2(x,y));
                    level.setEnemy2(e2);
                    e2.startWalking(-2);
                    //load the boss
                }else if(tokens[0].equals("Boss")){
                    Boss boss = new Boss(level);
                    boss.setPosition(new Vec2(18,33));
                    level.setBoss(boss);
                }

                line = reader.readLine();
            }
            return level;
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }

    }

}
