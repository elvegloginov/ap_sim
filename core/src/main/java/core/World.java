package core;

import Entity.Camera;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.DebugDrawer;
import com.badlogic.gdx.physics.bullet.collision.*;
import com.badlogic.gdx.physics.bullet.dynamics.*;
import com.badlogic.gdx.physics.bullet.linearmath.btIDebugDraw;
import com.badlogic.gdx.utils.Disposable;

public class World implements Disposable {
    public static final Vector3 DEFAULT_GRAVITY = new Vector3(0, -9.81f, 0f);
    private final btDynamicsWorld dynamicsWorld;
    private final btCollisionConfiguration collisionConfiguration;
    private final btDispatcher dispatcher;
    private final btConstraintSolver constraintSolver;
    private final btBroadphaseInterface broadphaseInterface;
    private final DebugDrawer debugDrawer;
    private final float fixedTimeStep = 1/60f;

    private final Vector3 lastRayFrom = new Vector3();
    private final Vector3 lastRayTo = new Vector3();
    private final Vector3 rayColor = new Vector3(1, 0, 1);

    public World(){
        collisionConfiguration = new btDefaultCollisionConfiguration();
        dispatcher = new btCollisionDispatcher(collisionConfiguration);
        broadphaseInterface = new btDbvtBroadphase();
        constraintSolver = new btSequentialImpulseConstraintSolver();
        dynamicsWorld = new btDiscreteDynamicsWorld(dispatcher, broadphaseInterface, constraintSolver, collisionConfiguration);

        debugDrawer = new DebugDrawer();
        debugDrawer.setDebugMode(btIDebugDraw.DebugDrawModes.DBG_DrawWireframe);
        dynamicsWorld.setDebugDrawer(debugDrawer);
    }

    public void update(float delta){
        dynamicsWorld.stepSimulation(delta, 5, fixedTimeStep);
    }

    public void render(Camera camera){
        //debugDrawer.begin(camera);
        debugDrawer.drawLine(lastRayFrom, lastRayTo, rayColor);
        dynamicsWorld.debugDrawWorld();
        debugDrawer.end();
    }

    public void addBody(btRigidBody body){
        dynamicsWorld.addRigidBody(body);
    }

    public void raycast(Vector3 from, Vector3 to, RayResultCallback callback){
        lastRayFrom.set(from).sub(0, 5f, 0f);
        dynamicsWorld.rayTest(from, to, callback);

        if(callback.hasHit() && callback instanceof ClosestRayResultCallback){
            lastRayTo.set(from);
            lastRayTo.lerp(to, callback.getClosestHitFraction());
        } else {
            lastRayTo.set(to);
        }
    }

    public void dispose(){
        collisionConfiguration.dispose();
        dispatcher.dispose();
        broadphaseInterface.dispose();
        constraintSolver.dispose();
        dynamicsWorld.dispose();
    }
}
