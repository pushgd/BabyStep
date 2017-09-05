package game.gameObjects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.libGDX.engine.Base.aniamtion.SpriteAnimation;
import com.libGDX.engine.Base.collision.Collision;
import com.libGDX.engine.Base.gameComponents.GameObject;
import com.libGDX.engine.Base.render.Image;
import com.libGDX.engine.Utility.Vector2D;

import game.Constant;

/**
 * Created by Dhande on 27/07/2017.
 */

public class CannonBall extends GameObject
{
    public CannonBall(float positionX, float positionY, float velocityX, float velocityY)
    {
        ID = Constant.ObjectIDs.CANNON_BALL;
        animation = new SpriteAnimation(this);
        animation.addAnimation(new TextureAtlas("ammo/bomb/cannon.atlas"), 1000);
        position = new Vector2D(positionX, positionY);
        velocity = new Vector2D(velocityX, velocityY);
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
        position.y += velocity.y;
        velocity.y += 0.6f;
        animation.update();
        if (position.y > Constant.SCREEN_HEIGHT)
        {
            remove = true;
        }
    }

    @Override
    public void paint(SpriteBatch spriteBatch)
    {
        animation.paint(spriteBatch, position.x - animation.getWidth() / 2, position.y - animation.getHeight() / 2);
        Image.Debug.fillColor(spriteBatch, position.x - 2, position.y - 2, 4, 4, Color.RED);
        Image.Debug.drawText(spriteBatch,position+"",position.x,position.y,Color.GREEN);

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
