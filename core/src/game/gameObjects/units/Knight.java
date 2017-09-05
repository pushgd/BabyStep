package game.gameObjects.units;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.MathUtils;
import com.libGDX.engine.Base.aniamtion.SpriteAnimation;
import com.libGDX.engine.Base.collision.Collision;
import com.libGDX.engine.Base.gameComponents.GameObject;
import com.libGDX.engine.Base.render.Image;
import com.libGDX.engine.Debug.Debug;
import com.libGDX.engine.Utility.Vector2D;
import com.sun.org.apache.regexp.internal.RE;

import game.BitmapCacher;
import game.CollisionManager;
import game.Constant;

/**
 * Created by Dhande on 02/08/2017.
 */

public class Knight extends GameObject
{


    public static final int STATE_WALK = 0;
    public static final int STATE_ATTACK = 1;
    public static final int STATE_DIE = 2;

    float gravity = 0.5f;
    float maxHp;
    float currentHP;
    float velocityX;
    public Knight(float positionX, float positionY)
    {

        ID = Constant.ObjectIDs.KNIGHT;
        velocityX = -2 - MathUtils.random(-1f, 1.5f);
        velocity = new Vector2D(velocityX, 0);
        position = new Vector2D(positionX, positionY);
        animation = new SpriteAnimation(this);
        animation.addAnimation(BitmapCacher.knightWalk, 800);
        animation.addAnimation(BitmapCacher.knightAttack, 1000);
        animation.addAnimation(BitmapCacher.knightDie, 2000);
        animation.setState(0, -1, true);
        collision = new Collision(this, -animation.getWidth() / 2, -animation.getHeight() / 2, animation.getWidth(), animation.getHeight());
        CollisionManager.addCollision(collision);
        currentHP = maxHp = 10;
    }


    @Override
    public void onAnimationStateComplete(int animationID, int completedState)
    {
        if (completedState == STATE_DIE)
        {
            remove = true;
        }
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
//        Debug.print("Knight UID "+UID+"  "+position);
        position.x += velocity.x;
        position.y += velocity.y;
        velocity.y += gravity;
        if (position.y + animation.getHeight() / 2 > Constant.GROUND)
        {
            position.y = Constant.GROUND - animation.getHeight() / 2;
            velocity.y = 0;
        }
        collision.update(-animation.getWidth() / 2, -animation.getHeight() / 2, animation.getWidth(), animation.getHeight());

        animation.update();
    }

    @Override
    public void paint(SpriteBatch spriteBatch)
    {
        animation.spriteAnimation.paint(spriteBatch, position.x - animation.getWidth() / 2, position.y - animation.getHeight() / 2, 0, 1, 1, true, false);
//        collision.paint(spriteBatch);
//        Image.Debug.fillColor(spriteBatch, position.x - 2, position.y - 2, 4, 4, Color.BLUE);
    }

    @Override
    public void onCollision(Collision collision, Collision otherCollision)
    {


        switch (otherCollision.owner.ID)
        {
            case Constant.ObjectIDs.CASTLE:
                if (animation.currentState != STATE_ATTACK)
                {
                    velocity.x = 0;
                    animation.setState(STATE_ATTACK, -1, true);
                }
                break;

            case Constant.ObjectIDs.ARROW:
                currentHP -= 2;
                if (currentHP <= 0)
                {
                    animation.setState(STATE_DIE, 1, false);
                    velocity.x = 0f;
                    collision.remove = true;
                }
                break;
            case Constant.ObjectIDs.PUSHING_OBJECT:
                if (animation.currentState == STATE_ATTACK)
                {
                    animation.setState(STATE_WALK, -1, false);
                    velocity.x = velocityX;
                }


                position.x = otherCollision.owner.collision.worldRect.right + animation.getWidth() / 2;

                break;
        }

    }

    @Override
    public void deallocate()
    {

    }
}
