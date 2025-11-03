package core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Vector4;
import net.dermetfan.utils.math.Noise;

import java.nio.ByteBuffer;

public class Camera extends Entity implements Movable{

    private float focalLength;
    private boolean pickedUp;
    private CameraInputBuilder cameraInputBuilder;

    public static final float FULLFRAME = 43.3f;
    public static final float MEDIUMFORMAT = FULLFRAME*0.64f;
    public static final float APSC = FULLFRAME*1.52f;
    public static final float MFT = FULLFRAME*2f;

    public static double ConvertMMtoFOV(float focalLength){
        return 2*Math.toDegrees(Math.atan(FULLFRAME/(2*focalLength)));
    }

    public static double ConvertMMtoFOV(float frameSize, float focalLength){
        return 2*Math.toDegrees(Math.atan(frameSize/(2*focalLength)));
    }

    public Camera(Model model) {
        super(model);
    }

    public Camera(Model model, Vector4 vector4) {
        super(model, vector4);
    }

    public Camera(Model model, Vector3 vector3, float theta) {
        super(model, vector3, theta);
    }

    public Camera(Model model, float x, float y, float z, float theta) {
        super(model, x, y, z, theta);
    }

    public void takePicture(){
        Pixmap pixmap = Pixmap.createFromFrameBuffer(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
        ByteBuffer pixels = pixmap.getPixels();

    }

}
