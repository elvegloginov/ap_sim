package ap.sim;

import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private CameraInputController cameraInputController;
    private Environment environment;
    private SpriteBatch batch;
    private ModelBatch modelBatch;
    private Texture image;
    private PerspectiveCamera cam;
    private Model model;
    private Model ground;
    private ModelInstance instance;
    private ModelInstance groundInstance;
    private ModelCache modelCache;

    @Override
    public void create() {
        modelCache = new ModelCache();
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight,0.4f,0.4f,0.4f,1f));
        environment.add(new DirectionalLight().set(0.8f,0.8f,0.8f,-1f,-0.8f,-0.2f));
        modelBatch = new ModelBatch();
        cam = new PerspectiveCamera(67, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(10f,10f,10f);
        cam.lookAt(0,0,0);
        cam.near=1f;
        cam.far=300f;
        cam.update();

        ModelBuilder modelBuilder = new ModelBuilder();
        model = modelBuilder.createBox(5f,5f,5f,new Material(ColorAttribute.createDiffuse(Color.GREEN)), VertexAttributes.Usage.Position| VertexAttributes.Usage.Normal);
        instance = new ModelInstance(model);
        ground = modelBuilder.createBox(25f,0.1f,25f,new Material(ColorAttribute.createDiffuse(Color.WHITE)),VertexAttributes.Usage.Position|VertexAttributes.Usage.Normal);
        instance = new ModelInstance(ground);
        //batch = new SpriteBatch();
        //image = new Texture("libgdx.png");

        cameraInputController = new CameraInputController(cam);
        Gdx.input.setInputProcessor(cameraInputController);
    }

    @Override
    public void render() {
        Gdx.gl.glViewport(0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT|GL20.GL_DEPTH_BUFFER_BIT);
        modelBatch.begin(cam);
        modelBatch.render(,environment);
        modelBatch.end();
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
