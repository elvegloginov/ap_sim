package core;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.utils.JsonReader;

public class ModelConverter {
    public static ModelInstance convert(String modelFileName){
        Model model = new G3dModelLoader(new JsonReader()).loadModel(Gdx.files.internal(modelFileName));
        return new ModelInstance(model);
    }
}
