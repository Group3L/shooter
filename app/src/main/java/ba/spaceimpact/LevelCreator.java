package ba.spaceimpact;

import android.content.Context;

import java.util.ArrayList;
import java.util.Random;

import ba.spaceimpact.GameObject.Coin;
import ba.spaceimpact.GameObject.EnemySpaceship;
import ba.spaceimpact.GameObject.GameObject;
import ba.spaceimpact.GameObject.PowerUp;
import ba.spaceimpact.GameObject.UserSpaceship;

/**
 * Created by pc on 15.12.2017.
 */

public class LevelCreator {

    public static ArrayList<GameObject> setGameObjects(Context context, UserSpaceship userSpaceship, int pixelX, int pixelY, int enemyCount, int powerupCount, int coinNumber, int level){
        ArrayList<GameObject> gameObjects = new ArrayList<>();
        Random random = new Random();

        for( int i = 0; i < enemyCount; i++) {
            float posX = (float) (Math.random() * (pixelX + 1));
            //float posY = (float)(-(Math.random() * (pixelY + 1)));
            //Enemies should appear out of screen and come to view

            float posY = -random.nextInt(10000) - 3000;

            float posX2 = (float) (Math.random() * (pixelX + 1));
            //Enemies should appear out of screen and come to view

            int speedY = random.nextInt(21) + 30;

            int typeRand = random.nextInt(EnemySpaceship.enemyType.values().length);

                EnemySpaceship e = new EnemySpaceship(5, 2, posX, posY, 0, speedY, context,
                        EnemySpaceship.enemyType.values()[typeRand]);

            //Checking if new spaceship intersects with existing ones
            gameObjects = positionCheck(e, gameObjects);
        }

        for(int i = 0; i < powerupCount; i++){
            int speedY = random.nextInt(31) + 10;
            float posX = (float) (Math.random() * (pixelX + 1));
            float posY = -random.nextInt(10000) - 1000;
            float posX2 = (float) (Math.random() * (pixelX + 1));
            float posY2 = -700;

            PowerUp e = null;
            int powerUpType = random.nextInt(5);
            switch (powerUpType){
                case 0: e = new PowerUp(PowerUp.SHIELD, userSpaceship, posX, posY, 0, speedY); break;
                case 1: e = new PowerUp(PowerUp.HEALTH_REGEN, userSpaceship, posX, posY, 0, speedY); break;
                case 2: e = new PowerUp(PowerUp.EXTRA_POINT, userSpaceship, posX, posY, 0, speedY); break;
                case 3: e = new PowerUp(PowerUp.INF_BULLET, userSpaceship, posX, posY, 0, speedY); break;
                case 4: e = new PowerUp(PowerUp.EXTRA_BULLET, userSpaceship, posX, posY, 0, speedY); break;
            }

            gameObjects = positionCheck(e, gameObjects);

        }

        for(int i = 0; i < coinNumber; i++){
            int speedY = random.nextInt(31) + 10;
            float posX = (float) (Math.random() * (pixelX + 1));
            float posY = -random.nextInt(10000) - 1000;
            float posX2 = (float) (Math.random() * (pixelX + 1));
            float posY2 = -700;

            Coin coin = new Coin( random.nextInt(6) + 5, userSpaceship, 0, speedY);

            //Checking if new object intersects with existing ones
            gameObjects = positionCheck(coin, gameObjects);
        }

        return gameObjects;
    }

    private static ArrayList<GameObject> positionCheck(GameObject gameObject, ArrayList<GameObject> gameObjects){
        if (gameObjects.size() > 0) {
            boolean cond = true;
            for (int j = 0; j < gameObjects.size(); j++) {
                if (gameObject.getRect().intersect(gameObjects.get(j).getRect())) {
                    cond = false;
                }
            }
            if (cond)
                gameObjects.add(gameObject);
        }
        else{
            gameObjects.add(gameObject);
        }

        return gameObjects;
    }



}
