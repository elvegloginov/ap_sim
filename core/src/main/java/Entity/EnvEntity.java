package Entity;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;

public class EnvEntity extends Entity{

    public EnvEntity(btRigidBody body, ModelInstance modelInstance) {
        super(body, modelInstance);
    }

    public EnvEntity(btRigidBody body, ModelInstance modelInstance, float x, float y, float z, float theta) {
        super(body, modelInstance, x, y, z, theta);
    }

    public EnvEntity(btRigidBody body, ModelInstance modelInstance, Vector3 vector3, float theta) {
        super(body, modelInstance, vector3, theta);
    }

    public EnvEntity(btRigidBody body, ModelInstance modelInstance, Vector4 vector4) {
        super(body, modelInstance, vector4);
    }
}
