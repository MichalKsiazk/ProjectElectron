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

public class Switch extends Component {

    public enum Type {
        MONOSTABLE, BISTABLE;
    }

    public enum State {
        OPEN, CLOSED;
    }

    public Type type;
    public State state;
    public State normalState;

    public Switch(Type type, State normalState, Entity entity) {
        this.entity = entity;
        this.type = type;
        this.normalState = normalState;
        this.state = this.normalState;
        colliders.add(new Collider2D(new Vector2(entity.position.x,entity.position.y), new Vector2(50,50), Collider2D.Type.RECTANGLE, true));
        connectors.add(new Connector(this.entity, new Vector2(-50,0)));
        connectors.add(new Connector(this.entity, new Vector2(50,0)));
    }

    @Override
    public void Update(ProgramInfo info) {
        super.RenderChildComponents(info);
        ChangeState(info.mouseInfo);
        Render(info.mainCamera.camera);
        colliders.get(0).pos = this.entity.position;
        ManageInputs(info.mouseInfo, 0);
    }
    void ChangeState(MouseInfo info) {
            if(info.L_STATE == 0)
                return;

            boolean click = info.L_STATE == 2 && colliders.get(0).hover;
            if (click) {
                switch (type) {
                    case MONOSTABLE:
                        if (normalState == State.OPEN)
                            state = State.CLOSED;
                        else
                            state = State.OPEN;
                        break;
                    case BISTABLE:
                        if (state == State.OPEN)
                            state = State.CLOSED;
                        else
                            state = State.OPEN;
                        break;
                }
            }
        boolean release = info.L_STATE == 3;
        if(!release)
            return;
        if (type == Type.MONOSTABLE) {
            if (normalState == State.OPEN)
                state = State.OPEN;
            else
                state = State.CLOSED;
        }
    }

    void Render(Camera cam){
        float s = entity.scale;
        r.setColor(color);
        r.setProjectionMatrix(cam.combined);
        r.identity();
        r.translate(entity.position.x, entity.position.y, 0);
        r.rotate(0, 0, 1, entity.rotation);
        r.begin(ShapeRenderer.ShapeType.Filled);
        r.rectLine(-50 * s, 0, -30 * s, 0, entity.width);
        r.rectLine(50 * s, 0, 30 * s, 0, entity.width);
        r.circle(-30 * s,0,4 * s);
        r.circle(30 * s,0,4 * s);
        float open = state == State.OPEN ? 20 * s : 0;
        r.rectLine(-30 * s, 0, 30 * s, open, entity.width);
        r.end();
    }
}
