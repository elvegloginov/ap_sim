package ap.sim;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.bullet.Bullet;
import core.CameraInputBuilder;
import core.Entity;
import core.EnvEntity;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private CameraInputController cameraInputController;
    private CameraInputBuilder cameraInputBuilder;
    private Environment environment;
    private SpriteBatch batch;
    private ModelBatch modelBatch;
    private Texture image;
    //private PerspectiveCamera cam;
    private Entity entity;
    private Model model;
    private Model ground;
    private ModelInstance instance;
    private ModelInstance instance2;
    private ModelInstance groundInstance;
    private ModelCache modelCache;

    @Override
    public void create() {
        com.badlogic.ashley.core.Entity entity1 = new com.badlogic.ashley.core.Entity();

        modelCache = new ModelCache();
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight,0.4f,0.4f,0.4f,1f));
        environment.add(new DirectionalLight().set(0.8f,0.8f,0.8f,-1f,-0.8f,-0.2f));
        modelBatch = new ModelBatch();
        //cam = new PerspectiveCamera(70, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createBox(5f,5f,5f,new Material(ColorAttribute.createDiffuse(Color.RED)), VertexAttributes.Usage.Position| VertexAttributes.Usage.Normal);
        ground = modelBuilder.createBox(100,100,0.1f, new Material(ColorAttribute.createDiffuse((Color.WHITE))), VertexAttributes.Usage.Position|VertexAttributes.Usage.Normal);
        entity = new EnvEntity(model, new Vector3(-10,2,3),0);
        instance = new ModelInstance(entity);
        instance2 = new ModelInstance(new EnvEntity(ground, 0, 0, 0,0));
        //batch = new SpriteBatch();
        //image = new Texture("libgdx.png");

        cameraInputController = new CameraInputController(cameraInputBuilder.getInstance());
        Gdx.input.setInputProcessor(cameraInputController);
    }

    @Override
    public void render() {
        Gdx.gl.glViewport(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT|GL20.GL_DEPTH_BUFFER_BIT);
        modelBatch.begin(cameraInputBuilder.getInstance());
        modelBatch.render(instance,environment);
        modelBatch.render(instance2,environment);
        modelBatch.end();
        instance.transform.translate(0.01f, 0, 0);
        Quaternion quaternion = new Quaternion(new Vector3(1,1,1), 0.1f);
        instance.transform.rotate(quaternion);
        cameraInputController.update();

        //ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);
        //batch.begin();
        //batch.draw(image, 140, 210);
        //batch.end();
        input();
        logic();
        draw();
    }

    private void input(){

    }

    private void logic(){

    }

    private void draw(){

    }


    @Override
    public void dispose() {
        modelBatch.dispose();
        batch.dispose();
        image.dispose();
        model.dispose();
    }
}
