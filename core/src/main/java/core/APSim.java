package core;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.physics.bullet.Bullet;

public class APSim extends Game {
    @Override
    public void create() {
        Bullet.init();
        setScreen(screen);
    }
}
