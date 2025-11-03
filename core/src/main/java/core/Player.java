package core;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Vector4;

public class Player extends Entity implements Movable{
    public Player(Model model) {
        super(model);
    }

    public Player(Model model, float x, float y, float z, float theta) {
        super(model, x, y, z, theta);
    }

    public Player(Model model, Vector3 vector3, float theta) {
        super(model, vector3, theta);
    }

    public Player(Model model, Vector4 vector4) {
        super(model, vector4);
    }


}
