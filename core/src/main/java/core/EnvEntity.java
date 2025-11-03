package core;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Vector4;

public class EnvEntity extends Entity{
    public EnvEntity(Model model) {
        super(model);
    }

    public EnvEntity(Model model, float x, float y, float z, float theta) {
        super(model, x, y, z, theta);
    }

    public EnvEntity(Model model, Vector3 vector3, float theta) {
        super(model, vector3, theta);
    }

    public EnvEntity(Model model, Vector4 vector4) {
        super(model, vector4);
    }
}
