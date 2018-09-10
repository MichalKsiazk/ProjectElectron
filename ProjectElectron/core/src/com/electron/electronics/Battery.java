package com.electron.electronics;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.electron.colliders.Collider2D;
import com.electron.entities.Entity;
import com.electron.entities.ProgramInfo;
import com.electron.input.MouseInfo;



public class Battery extends Component {
    private float outputVoltage;        //unit: V
    private float internalVoltage;      //unit: V
    private float maxCurrent;           //unit: A
    private float maxCapacity;          //unit: mAh
    private float currentCapacity;      //unit: mAh

    private float internalResistance;   //unit: ohm


    public Battery(float internalVoltage, float maxCurrent, float maxCapacity, Entity entity) {
        this.entity = entity;
        this.internalVoltage = internalVoltage;
        this.maxCurrent = maxCurrent;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = maxCapacity;
        colliders.add(new Collider2D(new Vector2(entity.position.x,entity.position.y), new Vector2(40,40), Collider2D.Type.RECTANGLE, true));
        connectors.add(new Connector(this.entity, new Vector2(-50,0)));
        connectors.add(new Connector(this.entity, new Vector2(50,0)));
    }



    @Override
    public void Update(ProgramInfo info){
        super.RenderChildComponents(info);
        colliders.get(0).pos = this.entity.position;
        ManageInputs(info.mouseInfo, 0);
        int t = entity.width;
        boolean hv = entity.rotation == 0 || entity.rotation % 180 == 0;
        r.setProjectionMatrix(info.mainCamera.camera.combined);
        r.begin(ShapeRenderer.ShapeType.Filled);
        r.setColor(color);
        r.identity();
        r.translate(entity.position.x,entity.position.y,0);
        r.rotate(0, 0, 1, entity.rotation);
        float s = entity.scale;
        r.rectLine(-70 * s, 0,-12 * s, 0,t);
        r.rectLine(-30 * s, 25 * s,-20 * s, 25 * s,t);
        if(hv)
            r.rectLine(30 * s, 25 * s,20 * s, 25 * s,t);
        else
            r.rectLine(25 * s, 30 * s,25 * s, 20 * s,t);

        r.rectLine(-25 * s, 30 * s,-25 * s, 20 * s,t);

        r.rectLine(-12 * s,-25 * s,-12 * s,25 * s,t);
        r.rectLine(70 * s, 0,12 * s, 0,t);
        r.rectLine(12 * s, -14 * s,12 * s, 14 * s,t);

        r.end();
        info.batch.begin();
        bf.setColor(color);
        if(hv)
            bf.draw(info.batch, "", entity.position.x, entity.position.y -40 * s);
        else
            bf.draw(info.batch, "", entity.position.x+40 * s, entity.position.y);
        info.batch.end();
    }

    public float getOutputVoltage() {
        return outputVoltage;
    }
    public float maxCurrent() {
        return maxCurrent;
    }
    public float maxCapacity() {
        return maxCapacity;
    }
    public float currentCapacity() {
        return currentCapacity;
    }
}
