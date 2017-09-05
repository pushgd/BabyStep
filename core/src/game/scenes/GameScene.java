package game.scenes;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.libGDX.engine.Base.gameComponents.GameObject;
import com.libGDX.engine.Base.gameComponents.Scene;
import com.libGDX.engine.Base.render.Image;
import com.libGDX.engine.Utility.Vector2D;

import game.Constant;
import game.GameObjectManager;
import game.gameObjects.PushingObject;

/**
 * Created by Dhande on 01-03-2017.
 */

public class GameScene extends Scene
{
    Image fireButton, knightButton, forceButton;
    Vector2D fireButtonPosition,knightButtonPosition,forceButtonPosition;

    public GameScene()
    {
        fireButton = new Image("buttons/fireButton.png");
        knightButton = new Image("buttons/knightButton.png");
        forceButton = new Image("buttons/forceButton.png");
        fireButtonPosition = new Vector2D( Constant.SCREEN_WIDTH * 0.09f , Constant.SCREEN_HEIGHT * 0.75f );
        knightButtonPosition = new Vector2D( Constant.SCREEN_WIDTH * 0.8f , Constant.SCREEN_HEIGHT * 0.9f );
        forceButtonPosition = new Vector2D( Constant.SCREEN_WIDTH * 0.2f , Constant.SCREEN_HEIGHT * 0.9f  );
        GameObjectManager.init();

    }


    @Override
    public void update()
    {
        GameObjectManager.update();
    }

    @Override
    public void pause()
    {

    }

    @Override
    public void paint(SpriteBatch spriteBatch)
    {

        GameObjectManager.paint(spriteBatch);
        Image.draw(spriteBatch, knightButton, knightButtonPosition.x - knightButton.getWidth() / 2, knightButtonPosition.y - knightButton.getHeight() / 2);


        Image.draw(spriteBatch, fireButton, fireButtonPosition.x - fireButton.getWidth() / 2, fireButtonPosition.y- fireButton.getHeight() / 2);

        Image.draw(spriteBatch, forceButton, forceButtonPosition.x - forceButton.getWidth() / 2, forceButtonPosition.y - forceButton.getHeight() / 2);
    }


    @Override
    public void keyDown(int keyCode)
    {

    }


    @Override
    public void keyUp(int keyCode)
    {
        if (keyCode == Input.Keys.LEFT)
        {
            GameObjectManager.marker.position.x -= 4;
        }
        else if (keyCode == Input.Keys.RIGHT)
        {
            GameObjectManager.marker.position.x += 4;
        }

        switch (keyCode)
        {
            case Input.Keys.A:

                GameObjectManager.castle.shootArrow(GameObjectManager.marker.position.x, GameObjectManager.marker.position.y,0);
                GameObjectManager.castle.shootArrow(GameObjectManager.marker.position.x, GameObjectManager.marker.position.y,40);
                GameObjectManager.castle.shootArrow(GameObjectManager.marker.position.x, GameObjectManager.marker.position.y,-40);
                GameObjectManager.marker.direction *= -1;
                break;

            case Input.Keys.S:
                GameObjectManager.castle.shootBomb(GameObjectManager.marker.position.x, GameObjectManager.marker.position.y);
                break;

            case Input.Keys.B:

                break;

            case Input.Keys.P:
                if(GameObjectManager.pushingObject.velocity.x<=0)
                GameObjectManager.pushingObject.velocity.x = PushingObject.VELOCITY;
                break;
            case Input.Keys.E:
                GameObjectManager.spawnEnemy(0);
                break;

        }

    }

    @Override
    public void touchUp(int screenX, int screenY, int pointer, int button)
    {
        if (isPointInside(screenX,screenY,knightButtonPosition.x,knightButtonPosition.y,knightButton))
        {
            keyUp(Input.Keys.E);
        }
        if (isPointInside(screenX,screenY,fireButtonPosition.x,fireButtonPosition.y,fireButton))
        {
            keyUp(Input.Keys.A);
        }
        if (isPointInside(screenX,screenY,forceButtonPosition.x,forceButtonPosition.y,forceButton))
        {
            keyUp(Input.Keys.P);
        }
    }

    public boolean isPointInside(float sx, float sy, float x, float y, Image image)
    {
        return sx > x - image.getWidth() / 2 &&
                sx < x + image.getWidth() / 2 &&
                sy > y - image.getHeight() / 2 &&
                sy < y + image.getHeight() / 2;

    }
}

