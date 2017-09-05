package game.gameObjects;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g3d.Material;
import com.libGDX.engine.Base.aniamtion.SpriteAnimation;
import com.libGDX.engine.Base.collision.Collision;
import com.libGDX.engine.Base.gameComponents.GameObject;
import com.libGDX.engine.Debug.Debug;
import com.libGDX.engine.Utility.Vector2D;

import game.BitmapCacher;
import game.CollisionManager;
import game.Constant;
import game.GameObjectManager;


/**
 * Created by Dhande on 27/07/2017.
 */

public class Castle extends GameObject
{

    private Vector2D shootingPoint;

    public Castle()
    {

        ID = Constant.ObjectIDs.CASTLE;
        animation = new SpriteAnimation(this);
        animation.addAnimation(new TextureAtlas(("castle/castle.atlas")), 1000);
        position = new Vector2D(Constant.SCREEN_WIDTH * 0.15f, 400);
        shootingPoint = new Vector2D(position.x, position.y - animation.getHeight() * 0.1f);
        collision = new Collision(this, -animation.getWidth() / 2, -animation.getHeight() / 2, animation.getWidth(), animation.getHeight());
        CollisionManager.addCollision(collision);
        BitmapCacher.loadArrow();

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

//        animation.update();
        collision.update(-animation.getWidth() / 2, -animation.getHeight() / 2, animation.getWidth(), animation.getHeight());
    }

    @Override
    public void paint(SpriteBatch spriteBatch)
    {
        animation.paint(spriteBatch, position.x - animation.getWidth() / 2, position.y - animation.getHeight() / 2);
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

    public void shootArrow(float targetX, float targetY,float offset)
    {

        float range = (targetX - shootingPoint.x)+offset;
        float height = -(targetY - shootingPoint.y);
        float initialVelocity = 10;
        float angleOfLaunch;
        float g = 0.6f;
        float time = (0.7f) * 60;

        angleOfLaunch = (float) (Math.atan((height + (0.5 * g * time * time)) / range));
        initialVelocity = (float) (range / (time * Math.cos(angleOfLaunch)));


        Arrow arrow = new Arrow(shootingPoint.x, shootingPoint.y, (float) (initialVelocity * Math.cos(angleOfLaunch)), -(float) (initialVelocity * Math.sin(angleOfLaunch)));
        GameObjectManager.gameObjectList.add(arrow);


    }

    public void shootBomb(float targetX, float targetY)
    {

        float range = (targetX - shootingPoint.x);
        float height = -(targetY - shootingPoint.y);
        float initialVelocity = 10;
        float angleOfLaunch;
        float g = 0.6f;
        float time = 1.3f * 60;

        angleOfLaunch = (float) (Math.atan((height + (0.5 * g * time * time)) / range));
        initialVelocity = (float) (range / (time * Math.cos(angleOfLaunch)));


        CannonBall cannonBall = new CannonBall(shootingPoint.x, shootingPoint.y, (float) (initialVelocity * Math.cos(angleOfLaunch)), -(float) (initialVelocity * Math.sin(angleOfLaunch)));
        GameObjectManager.gameObjectList.add(cannonBall);

    }


}
