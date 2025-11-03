package core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.math.Vector3;

import java.util.Vector;

public class CameraInputBuilder {

    private PerspectiveCamera cameraInstance = null;

    public PerspectiveCamera getInstance(){
        if(cameraInstance == null){
            cameraInstance = new PerspectiveCamera(67, 200, 200);
            cameraInstance.position.set(0, 0, 0);
            cameraInstance.near = 1f;
            cameraInstance.far = 300f;
            return cameraInstance;
        }
        else{
            return cameraInstance;
        }
    }

    public void stop(){
        cameraInstance = null;
    }


}
