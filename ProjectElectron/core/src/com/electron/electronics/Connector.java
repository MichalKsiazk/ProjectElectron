package com.electron.electronics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.electron.colliders.Collider2D;
import com.electron.entities.Entity;
import com.electron.entities.ProgramInfo;

public class Connector {
    public Entity entity;
    protected ShapeRenderer r;
    public Color color;
    public Collider2D collider;
    public float size = 8;
    Vector2 fx_pos;

    public Connector(Entity entity, Vector2 pos)  {

        this.fx_pos = new Vector2(pos.x, pos.y);
        r = new ShapeRenderer();
        this.entity = new Entity(new Vector2(0, 0), 0, entity.scale,entity.width);
        this.collider = new Collider2D(this.entity.position, new Vector2(size * this.entity.scale,0), Collider2D.Type.CIRCLE,true);
    }

    public void Update(ProgramInfo programInfo, Vector2 new_pos, Entity base_entity, Color color){
        Color render_color = color;
        float fx_size = size/2;

        if(base_entity.rotation == 0 || base_entity.rotation % 180 == 0)
            entity.position = new Vector2(new_pos.x + fx_pos.x, new_pos.y + fx_pos.y);
        else
            entity.position = new Vector2(new_pos.y + fx_pos.y, new_pos.y + fx_pos.x);

        collider.pos = entity.position;
        collider.Update(programInfo.mouseInfo, programInfo.mainCamera.camera);
        if(collider.hover) {
            render_color = new Color(1, 0.4f, 0, 1);
            if(programInfo.mouseInfo.L_STATE == 2 && !programInfo.componentManager.setting_wire) {
                Wire wire = new Wire(new Entity(entity.position, 0, 1, 1));
                wire.entity.position = new Vector2(0,0);
                wire.connectors.add(this);
                wire.start = entity.position;
                wire.drag = true;
                programInfo.componentManager.componentsToAdd.add(wire);
            }
        }
        r.setProjectionMatrix(programInfo.mainCamera.camera.combined);
        r.begin(ShapeRenderer.ShapeType.Filled);
        r.setColor(render_color);
        r.circle(entity.position.x, entity.position.y, 4 * entity.scale);
        r.end();
    }
}
