package Entity;

import CameraPOV.CameraInputBuilder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;

import java.nio.ByteBuffer;

public class Camera extends Entity implements Movable {

    private float focalLength;
    private boolean pickedUp;
    private CameraInputBuilder cameraInputBuilder;

    public static final float FULLFRAME = 43.3f;
    public static final float MEDIUMFORMAT = FULLFRAME*0.64f;
    public static final float APSC = FULLFRAME*1.52f;
    public static final float MFT = FULLFRAME*2f;

    public Camera(btRigidBody body, ModelInstance modelInstance) {
        super(body, modelInstance);
    }

    public Camera(btRigidBody body, ModelInstance modelInstance, float x, float y, float z, float theta) {
        super(body, modelInstance, x, y, z, theta);
    }

    public Camera(btRigidBody body, ModelInstance modelInstance, Vector3 vector3, float theta) {
        super(body, modelInstance, vector3, theta);
    }

    public Camera(btRigidBody body, ModelInstance modelInstance, Vector4 vector4) {
        super(body, modelInstance, vector4);
    }

    public static double ConvertMMtoFOV(float focalLength){
        return 2*Math.toDegrees(Math.atan(FULLFRAME/(2*focalLength)));
    }

    public static double ConvertMMtoFOV(float frameSize, float focalLength){
        return 2*Math.toDegrees(Math.atan(frameSize/(2*focalLength)));
    }


    public void takePicture(){
        Pixmap pixmap = Pixmap.createFromFrameBuffer(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
        ByteBuffer pixels = pixmap.getPixels();

    }

}
