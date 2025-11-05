package Entity;

import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;

public abstract class Entity {
    private float x,y,z, theta;
    private Model model;
    private Vector3 position;
    private Vector4 rotation;
    public static long entityId = 0;
    private long privateId = 0;
    private final btRigidBody body;
    private final ModelInstance modelInstance;

    public Entity(btRigidBody body, ModelInstance modelInstance){
        this.body = body;
        this.modelInstance = modelInstance;
        setX(0);
        setY(0);
        setZ(0);
        setTheta(0);
        setPosition(new Vector3());
        setRotation(new Vector4(getPosition(),0));
        setPrivateId(entityId++);
    }

    public Entity(btRigidBody body, ModelInstance modelInstance, float x, float y, float z, float theta){
        this.body = body;
        this.modelInstance = modelInstance;
        setX(x);
        setY(y);
        setZ(z);
        setTheta(theta);
        setPosition(new Vector3(getX(),getY(),getZ()));
        setRotation(new Vector4(getPosition(), theta));
    }

    public Entity(btRigidBody body, ModelInstance modelInstance, Vector3 vector3, float theta){
        this.body = body;
        this.modelInstance = modelInstance;
        setPosition(vector3);
        setRotation(new Vector4(vector3, theta));
    }

    public Entity(btRigidBody body, ModelInstance modelInstance, Vector4 vector4){
        this.body = body;
        this.modelInstance = modelInstance;
        setRotation(vector4);
    }

    public static long getEntityId() {
        return entityId;
    }

    public void incX(float inc){
        setX(getX()+inc);
    }

    public void decX(float dec){
        setX(getX()-dec);
    }

    public void incY(float inc){
        setY(getY()+inc);
    }

    public void decY(float dec){
        setY(getY()-dec);
    }

    public void incZ(float inc){
        if(inc<0)
        setX(getZ()+inc);
    }

    public void decZ(float dec){
        setX(getZ()-dec);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getTheta() {
        return theta;
    }

    public void setTheta(float theta) {
        this.theta = theta;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Vector3 getPosition() {
        return position;
    }

    public void setPosition(Vector3 position) {
        this.position = position;
    }

    public Vector4 getRotation() {
        return rotation;
    }

    public void setRotation(Vector4 rotation) {
        this.rotation = rotation;
    }


    public long getPrivateId() {
        return privateId;
    }

    public void setPrivateId(long privateId) {
        this.privateId = privateId;
    }

    public ModelInstance getModelInstance(){
        return modelInstance;
    }

    public btRigidBody getBody(){
        return body;
    }
}
