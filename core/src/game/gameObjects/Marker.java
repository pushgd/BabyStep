package game.gameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.libGDX.engine.Base.collision.Collision;
import com.libGDX.engine.Base.gameComponents.GameObject;
import com.libGDX.engine.Base.render.Image;
import com.libGDX.engine.Utility.Vector2D;


import game.Constant;
import game.GameObjectManager;

/**
 * Created by Dhande on 27/07/2017.
 */

public class Marker extends GameObject
{
    public static final float RIGT_BOUND = Constant.SCREEN_WIDTH * 0.95f;
    public static final float LEFT_BOUND = GameObjectManager.castle.position.x + GameObjectManager.castle.animation.getWidth() / 2;
    public Image markerImage;
    public int direction = 1;

    public Marker()
    {
        markerImage = new Image("marker.png");
        position = new Vector2D(Constant.SCREEN_WIDTH / 2, Constant.GROUND);
        velocity = new Vector2D(3, 0);
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
        position.x += velocity.x * direction;


        if (position.x >= RIGT_BOUND || position.x <= LEFT_BOUND)
        {

            if (position.x > RIGT_BOUND)
            {
                position.x = RIGT_BOUND;
            }
            if (position.x < LEFT_BOUND)
            {
                position.x = LEFT_BOUND;
            }
            direction = -direction;
        }

    }

    @Override
    public void paint(SpriteBatch spriteBatch)
    {
        Image.draw(spriteBatch, markerImage, position.x - markerImage.getWidth() / 2, position.y);
//        Image.Debug.fillColor(spriteBatch, position.x - 2, position.y - 2, 4, 4, Color.YELLOW);
//        Image.Debug.drawText(spriteBatch,position+"",position.x,position.y,Color.GREEN);
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
