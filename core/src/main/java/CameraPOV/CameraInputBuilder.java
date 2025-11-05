package CameraPOV;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.IntIntMap;

public class CameraInputBuilder extends CameraController {

    private PerspectiveCamera cameraInstance;
    private final IntIntMap keys = new IntIntMap();
    private int LEFT = Input.Keys.A;
    private int RIGHT = Input.Keys.D;
    private int FORWARD = Input.Keys.W;
    private int BACKWARD = Input.Keys.S;
    private int UP = Input.Keys.Q;
    private int DOWN = Input.Keys.E;
    private float velocity = 5;
    private float degreesPerPixel = 0.5f;
    private final Vector3 tmp = new Vector3();

    public CameraInputBuilder(Camera camera){
        this.cameraInstance = (PerspectiveCamera) camera;
    }

    @Override
    public boolean keyDown(int keycode){
        keys.put(keycode, keycode);
        return true;
    }

    @Override
    public boolean keyUp(int keycode){
        keys.remove(keycode, 0);
        return true;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public void setDegreesPerPixel(float degreesPerPixel) {
        this.degreesPerPixel = degreesPerPixel;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer){
        float deltaX = -Gdx.input.getDeltaX() * degreesPerPixel;
        float deltaY = -Gdx.input.getDeltaY() * degreesPerPixel;
        cameraInstance.direction.rotate(cameraInstance.up, deltaX);
        tmp.set(cameraInstance.direction).crs(cameraInstance.up).nor();
        cameraInstance.direction.rotate(tmp,deltaY);
        return true;
    }

    @Override
    public void update(float deltaTime) {
        if(keys.containsKey(FORWARD)){
            tmp.set(cameraInstance.direction).nor().scl(deltaTime * velocity);
            cameraInstance.position.add(tmp);
        }
        if (keys.containsKey(BACKWARD)) {
            tmp.set(cameraInstance.direction).nor().scl(-deltaTime * velocity);
            cameraInstance.position.add(tmp);
        }
        if (keys.containsKey(LEFT)) {
            tmp.set(cameraInstance.direction).crs(cameraInstance.up).nor().scl(-deltaTime * velocity);
            cameraInstance.position.add(tmp);
        }
        if (keys.containsKey(RIGHT)) {
            tmp.set(cameraInstance.direction).crs(cameraInstance.up).nor().scl(deltaTime * velocity);
            cameraInstance.position.add(tmp);
        }
        if (keys.containsKey(UP)) {
            tmp.set(cameraInstance.up).nor().scl(deltaTime * velocity);
            cameraInstance.position.add(tmp);
        }
        if (keys.containsKey(DOWN)) {
            tmp.set(cameraInstance.up).nor().scl(-deltaTime * velocity);
            cameraInstance.position.add(tmp);
        }
        cameraInstance.update(true);
    }
}
