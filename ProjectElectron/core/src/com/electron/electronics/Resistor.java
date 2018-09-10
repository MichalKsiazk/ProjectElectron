package com.electron.electronics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.electron.entities.Entity;
import com.electron.entities.ProgramInfo;
import com.electron.input.MouseInfo;

public class Resistor extends Component {

    public float resistance;
    public float maxPower;

    public Resistor(float resistance, float maxPower, Entity entity){
        this.entity = entity;
        this.resistance = resistance;
        this.maxPower = maxPower;
    }

    @Override
    public void Update(ProgramInfo info) {
        r.setProjectionMatrix(info.mainCamera.camera.combined);
        r.translate(0, 0, 0);
        r.identity();
        r.translate(entity.position.x, entity.position.y, 0);
        r.rotate(0, 0, 1, entity.rotation);
        r.begin(ShapeRenderer.ShapeType.Filled);
        r.setColor(color);
        float w = entity.width;
        r.rectLine(-70, 0,-50, 0, w);
        r.rectLine(70, 0,50, 0, w);
        r.end();
        r.begin(ShapeRenderer.ShapeType.Line);
        r.rect(-50,-20,100,40);
        r.end();
        info.batch.begin();
        BitmapFont font = new BitmapFont();
        font.setColor(color);
        font.draw(info.batch, "", entity.position.x, entity.position.y);
        info.batch.end();
    }
}
