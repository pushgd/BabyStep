package game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.libGDX.engine.Base.gameComponents.GameObject;
import com.libGDX.engine.Base.render.Image;

import java.util.ArrayList;

import game.gameObjects.Castle;
import game.gameObjects.Marker;
import game.gameObjects.PushingObject;
import game.gameObjects.units.Knight;

/**
 * Created by Dhande on 01-03-2017.
 */

public class GameObjectManager
{
    public static ArrayList<GameObject> gameObjectList;
    private static Image backGround;
    public static Castle castle;
    public static Marker marker;
    public static PushingObject pushingObject;

    private GameObjectManager()
    {


    }

    public static void update()
    {

        for (int i = 0; i < gameObjectList.size(); i++)
        {
            GameObject gameObject = gameObjectList.get(i);
            gameObject.update();
            if (gameObject.remove)
            {
                gameObjectList.remove(i);
                i--;
            }
        }
        CollisionManager.detectCollision();
    }


    public static void paint(SpriteBatch spriteBatch)
    {
        Image.draw(spriteBatch, backGround, 0, 0);
        for (int i = 0; i < gameObjectList.size(); i++)
        {
            gameObjectList.get(i).paintEntity(spriteBatch);
        }
        Image.Debug.drawText(spriteBatch, "" + gameObjectList.size(), 0, 20);
        Image.Debug.drawText(spriteBatch, "" + CollisionManager.collisionList.size(), 0, 40);
    }

    public static void init()
    {
        BitmapCacher.loadKnightAnimation();
        gameObjectList = new ArrayList<GameObject>();
        castle = new Castle();
        gameObjectList.add(castle);
        marker = new Marker();
        gameObjectList.add(marker);
        spawnEnemy(0);
        pushingObject = new PushingObject();
        gameObjectList.add(pushingObject);

        backGround = new Image("backGrounds/bg.png");
    }

    public static void spawnEnemy(int enemy)
    {
        gameObjectList.add(new Knight(1300, Constant.GROUND - 50));
    }
}
