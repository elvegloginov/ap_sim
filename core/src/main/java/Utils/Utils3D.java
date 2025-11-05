package Utils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.BoxShapeBuilder;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.CapsuleShapeBuilder;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.ConeShapeBuilder;
import com.badlogic.gdx.graphics.g3d.utils.shapebuilders.SphereShapeBuilder;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public class Utils3D {
    private static final Vector3 tmpVec = new Vector3();

    public static void getDirection(Matrix4 transform, Vector3 out) {
        tmpVec.set(Vector3.Z);
        out.set(tmpVec.rot(transform).nor());
    }

    public static void getPosition(Matrix4 transform, Vector3 out) {
        transform.getTranslation(out);
    }

    public static Model loadOBJ(FileHandle fileHandle) {
        ObjLoader loader = new ObjLoader();
        return loader.loadModel(fileHandle);
    }

    public static Model buildCapsuleCharacter() {
        ModelBuilder modelBuilder = new ModelBuilder();
        modelBuilder.begin();

        Material bodyMaterial = new Material();
        bodyMaterial.set(ColorAttribute.createDiffuse(Color.YELLOW));

        Material armMaterial = new Material();
        armMaterial.set(ColorAttribute.createDiffuse(Color.BLUE));

        // Build the cylinder body
        MeshPartBuilder builder = modelBuilder.part("body", GL20.GL_TRIANGLES, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal, bodyMaterial);
        CapsuleShapeBuilder.build(builder, .5f, 2f, 12);

        builder = modelBuilder.part("arms", GL20.GL_TRIANGLES, VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal, armMaterial);
        BoxShapeBuilder.build(builder, .5f, 0, 0f, .25f,1f,.25f);
        BoxShapeBuilder.build(builder, -.5f, 0, 0f, .25f,1f,.25f);

        // Left Eye
        builder.setVertexTransform(new Matrix4().trn(-.15f,.5f,.5f));
        SphereShapeBuilder.build(builder, .15f, .15f, .15f, 12,12);

        // Right Eye
        builder.setVertexTransform(new Matrix4().trn(.15f,.5f,.5f));
        SphereShapeBuilder.build(builder, .15f, .15f, .15f, 12,12);

        // Finish building
        return modelBuilder.end();
    }

}
