package com.electron.electronics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.electron.colliders.Collider2D;
import com.electron.entities.Entity;
import com.electron.entities.ProgramInfo;
import com.electron.input.MouseInfo;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.List;

public class Wire extends Component {

    public Vector2 start;
    public Vector2 stop;
    boolean first_frame = true;
    boolean drag = false;
    boolean finished = false;
    boolean inverted = false;
    public Wire(Entity entity){
        name = "wire";
        this.entity = entity;
        Collider2D c = new Collider2D(entity.position, new Vector2(10 * entity.scale, 0), Collider2D.Type.CIRCLE, true);
        colliders.add(c);
    }


    @Override
    public void Update(ProgramInfo info) {
        if(first_frame) {
            first_frame = false;
            info.componentManager.setting_wire = true;
        }
        super.RenderChildComponents(info);
        SetWire(info);
        if(start == null || stop == null)
            return;
        r.identity();
        r.translate(entity.position.x, entity.position.y, 0);
        r.setProjectionMatrix(info.mainCamera.camera.combined);
        r.begin(ShapeRenderer.ShapeType.Filled);
        r.setColor(color);
        if(inverted) {
            r.rectLine(start.x, start.y, stop.x, start.y, entity.width);
            r.circle(stop.x, start.y, 4 * entity.scale);
            r.rectLine(stop.x, start.y, stop.x, stop.y, entity.width);
            colliders.get(0).pos = new Vector2(stop.x, start.y);
        }
        else {
            r.rectLine(start.x, start.y, start.x, stop.y, entity.width);
            r.circle(start.x, stop.y, 4 * entity.scale);
            r.rectLine(stop.x, stop.y, start.x, stop.y, entity.width);
            colliders.get(0).pos = new Vector2(start.x, stop.y);
        }
        //r.circle(start.x, start.y, entity.scale * 4);
        //r.circle(stop.x, stop.y, entity.scale * 4);
        r.end();
    }

    private void SetWire(ProgramInfo info) {
        if(Gdx.input.isKeyJustPressed(Input.Keys.R) && !finished){
            inverted = !inverted;
        }
        if(drag && info.mouseInfo.L_STATE == 2 && !finished){
            finished = true;
            Connector c = HoveredConnector(info);
            if(c == null)
                stop = new Vector2(info.mouseInfo.x - info.mouseInfo.fixed_x, Gdx.graphics.getHeight() - info.mouseInfo.y - info.mouseInfo.fixed_y);
            else {
                System.out.println("CONNECTOR FOUND");
                stop = c.fx_pos;
                connectors.add(c);
            }
            connectors.add(new Connector(entity,stop));
            info.componentManager.setting_wire = false;
        }

        if(info.mouseInfo.L_STATE == 2 && !drag && !finished){
            drag = true;
            start = new Vector2(info.mouseInfo.x - info.mouseInfo.fixed_x, Gdx.graphics.getHeight()  - info.mouseInfo.y - info.mouseInfo.fixed_y);
            connectors.add(new Connector(entity,start));
            return;
        }
        else if(drag && !finished){
            stop = new Vector2(info.mouseInfo.x - info.mouseInfo.fixed_x, Gdx.graphics.getHeight() - info.mouseInfo.y - info.mouseInfo.fixed_y);
            return;
        }
        if(colliders.get(0).hover && finished) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.R))
                inverted = !inverted;
            if (Gdx.input.isKeyJustPressed(Input.Keys.X))
                die = true;
        }
    }
    private Connector HoveredConnector(ProgramInfo info){
        List<Component> components = info.componentManager.components;
        for(Component component : components) {
            for(Connector connector : component.connectors) {
                if (connector.collider.hover) {
                    System.out.println("CONNECTOR FOUND");
                    return connector;
                }
            }
        }
        return null;
    }
}
