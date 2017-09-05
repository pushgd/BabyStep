package game.gameObjects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.libGDX.engine.Base.collision.Collision;
import com.libGDX.engine.Base.gameComponents.GameObject;
import com.libGDX.engine.Utility.Vector2D;

import game.CollisionManager;
import game.Constant;
import game.GameObjectManager;

/**
 * Created by Dhande on 03/08/2017.
 */

public class PushingObject extends GameObject
{
    public static final float HEIGHT = 50;
    public static final float WIDTH = 50;
    public static final float VELOCITY = 12;

    public PushingObject()
    {
        ID = Constant.ObjectIDs.PUSHING_OBJECT;
        position = new Vector2D(GameObjectManager.castle.position.x + GameObjectManager.castle.animation.getWidth() / 2, Constant.GROUND - HEIGHT);
        velocity = new Vector2D(0, 0);
        collision = new Collision(this, 0, 0, WIDTH, HEIGHT);
        CollisionManager.addCollision(collision);
    }


    @Override
    public void onAnimationStateComplete(int animationID, int completedState)
    {

    }

    @Override
    public void onAnimationCycleComplete(int animationID, int completedState, int noOfCyclesRemaining)
    {

    }

    @Override
    public void onAnimationEvent(float eventNumber, float animationState)
    {

    }

    @Override
    public void update()
    {

        position.x += velocity.x;
        velocity.x -= 0.2;
        if (velocity.x < 0)
        {
            position.x = GameObjectManager.castle.position.x + GameObjectManager.castle.animation.getWidth() / 2-WIDTH*1.5f;
            position.y = Constant.GROUND - HEIGHT;
            velocity.x = 0;
        }
        collision.update(0, 0, WIDTH, HEIGHT);
    }

    @Override
    public void paint(SpriteBatch spriteBatch)
    {
//        collision.paint(spriteBatch);
  }

    @Override
    public void onCollision(Collision collision, Collision otherCollision)
    {

    }

    @Override
    public void deallocate()
    {

    }
}
