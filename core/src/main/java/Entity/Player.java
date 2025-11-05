package Entity;

import Utils.Utils3D;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.Vector4;
import com.badlogic.gdx.physics.bullet.collision.ClosestNotMeRayResultCallback;
import com.badlogic.gdx.physics.bullet.collision.ClosestRayResultCallback;
import com.badlogic.gdx.physics.bullet.dynamics.btRigidBody;
import core.World;

public class Player {
    private final float MOVE_SPEED = 10f;
    private final float JUMP_FACTOR = 15f;

    private final Vector3 normal = new Vector3();
    private final Vector3 tmpPosition = new Vector3();
    private final Vector3 currentDirection = new Vector3();
    private final Vector3 linearVelocity = new Vector3();
    private final Vector3 angularVelocity = new Vector3();

    private final Entity player;
    private final World physicsSystem;
    private final ClosestNotMeRayResultCallback callback;

    public Player(Entity entity, World physicsSystem){
        this.player = entity;
        this.physicsSystem = physicsSystem;
         callback = new ClosestNotMeRayResultCallback(player.getBody());
    }

    public void update(float delta){
        Utils3D.getDirection(player.getModelInstance().transform, currentDirection);
        btRigidBody body = player.getBody();

        resetVelocity();

        boolean isOnGround = isGrounded();

            if(isOnGround) {
        callback.getHitNormalWorld(normal);

        float dot = normal.dot(Vector3.Y);

        if (dot != 1.0) {
            body.setGravity(Vector3.Zero);
        }
    } else {
        body.setGravity(World.DEFAULT_GRAVITY);
    }

    // Forward movement
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
        linearVelocity.set(currentDirection).scl(delta * MOVE_SPEED);
    } else if (Gdx.input.isKeyPressed(Input.Keys.S)) {
        linearVelocity.set(currentDirection).scl(-delta * MOVE_SPEED);
    }


        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
        angularVelocity.set(0, 1f, 0);
    } else if (Gdx.input.isKeyPressed(Input.Keys.D)) {
        angularVelocity.set(0, -1f, 0);
    }

    // Jump
        if (isOnGround && Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
        linearVelocity.y += JUMP_FACTOR;
    }

        if (!linearVelocity.isZero()) {
        body.applyCentralImpulse(linearVelocity);
    }

        if (!angularVelocity.isZero()) {
        body.setAngularVelocity(angularVelocity);
    }

}

private boolean isGrounded() {
    callback.setClosestHitFraction(1.0f);
    callback.setCollisionObject(null);

    Utils3D.getPosition(player.getModelInstance().transform, player.getPosition());

    tmpPosition.set(player.getPosition()).sub(0, 1.4f, 0);

    physicsSystem.raycast(player.getPosition(), tmpPosition, callback);

    return callback.hasHit();
}

private void resetVelocity() {
    angularVelocity.set(0,0,0);
    linearVelocity.set(0,0,0);
}


}
