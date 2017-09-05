package game;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

/**
 * Created by Dhande on 02/08/2017.
 */

public class BitmapCacher
{

    public static TextureAtlas knightAttack, knightWalk, knightDie;
    public static TextureAtlas arrow;

    public static void loadKnightAnimation()
    {
        knightAttack = new TextureAtlas("units/knight/attack/knightAttack.atlas");
        knightDie = new TextureAtlas("units/knight/die/knightDie.atlas");
        knightWalk = new TextureAtlas("units/knight/walk/knightWalk.atlas");
    }


    public static void loadArrow()
    {
        arrow = new TextureAtlas("ammo/arrow/arrow.atlas");
    }


}
