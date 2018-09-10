package com.electron.electronics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.electron.entities.Entity;
import com.electron.entities.ProgramInfo;
import com.electron.input.MouseInfo;

public class Bulb extends Component {

    public Bulb(Entity entity) {
        this.entity = entity;
    }

    @Override
    public void Update(ProgramInfo info) {
        r.identity();
        r.translate(entity.position.x, entity.position.y, 0f);
        r.rotate(0, 0, 1, entity.rotation);
        r.setProjectionMatrix(info.mainCamera.camera.combined);
        r.begin(ShapeRenderer.ShapeType.Filled);

        r.setColor(color);
        float dst = (float)Math.sqrt(40 * 40 / 2);
        r.rectLine(-dst, -dst, dst, dst, entity.width);
        r.rectLine(-dst, dst, dst, -dst, entity.width);
        r.end();
        r.begin(ShapeRenderer.ShapeType.Line);
        r.circle(0, 0, 40 * entity.scale);
        r.end();
    }
}
