package com.electron.electronics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.electron.colliders.Collider2D;
import com.electron.entities.Entity;
import com.electron.entities.ProgramInfo;
import com.electron.input.MouseInfo;

import java.util.ArrayList;
import java.util.List;

public class Component {


    public String name;
    public List<String> tags;
    public int id;

    public boolean die = false;
    public Color color;
    protected BitmapFont bf;
    protected ShapeRenderer r;
    public Entity entity;
    public List<Component> childComponents;
    public List<Connector> connectors;
    public float inputVoltage;

    public float resistance;
    public List<Collider2D> colliders = new ArrayList<Collider2D>();
    public void Update(ProgramInfo programInfo){}
    public Component(){
        name = "";
        tags = new ArrayList<String>();
        color = new Color(1,1,0,1);
        r = new ShapeRenderer();
        bf = new BitmapFont();
        this.entity = new Entity(new Vector2(0,0), 0,1,1);
        childComponents = new ArrayList<Component>();
        connectors = new ArrayList<Connector>();
    }
    protected boolean RaycastClick(int x, int y) {
        float dst = Vector2.dst(entity.position.x, Gdx.graphics.getHeight() - entity.position.y, x , y);
        return dst < 100 * entity.scale ? true : false;
    }

    protected void ManageInputs(MouseInfo info, int index) {
        if(colliders.get(index).hover && Gdx.input.isKeyJustPressed(Input.Keys.X))
            die = true;
        if(info.R_STATE == 1 && colliders.get(index).hover){
            entity.position.x += Gdx.input.getDeltaX();
            entity.position.y -= Gdx.input.getDeltaY();
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.R) && colliders.get(index).hover){
            entity.rotation += 90;
        }

    }
    protected void RenderChildComponents(ProgramInfo info){
        for(Component l : childComponents){
            if(l == null)
                continue;
            if(l.die) {
                l = null;
                continue;
            }
            l.Update(info);
            for (Collider2D col : l.colliders) {
                col.Update(info.mouseInfo, info.mainCamera.camera);
            }
        }
        for (Connector c : connectors) {
            c.Update(info, entity.position, entity, color);
        }
    }
}
