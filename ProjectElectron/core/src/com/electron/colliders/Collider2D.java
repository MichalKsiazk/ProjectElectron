package com.electron.colliders;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.electron.input.MouseInfo;
import jdk.nashorn.internal.runtime.Debug;

public class Collider2D {
    public enum Type{
        CIRCLE, RECTANGLE
    }

    public Type type;
    public Vector2 pos;
    public Vector2 dim;


    public boolean hover;
    public boolean pressed;

    public boolean visualize;
    ShapeRenderer r;
    private int w, h;

    public Collider2D(Vector2 pos, Vector2 dim, Type type, boolean visualize) {
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        r = new ShapeRenderer();
        this.pos = pos;
        this.dim = dim;
        this.type = type;
        this.visualize = visualize;
    }

    public void Update(MouseInfo info, OrthographicCamera cam) {
        if(hover)
            r.setColor(0,1,0,1);
        else
            r.setColor(0,0,1,1);
        switch(type){
            case CIRCLE:
                Circle(info, cam);
                break;
            case RECTANGLE:
                Rectangle(info, cam);
                break;
        }
    }

    private void Circle(MouseInfo info, Camera cam){
        if(Vector2.dst( info.x - info.fixed_x, h - info.y - info.fixed_y, pos.x, pos.y) <= dim.x/2){
            hover = true;
        }
        else hover = false;
        if(!visualize)
            return;
        r.setProjectionMatrix(cam.combined);
        r.begin(ShapeRenderer.ShapeType.Line);
        r.circle(pos.x, pos.y, dim.x/2);
        r.end();
    }

    private void Rectangle(MouseInfo info, Camera cam){
        if(visualize) {
            r.setProjectionMatrix(cam.combined);
            r.begin(ShapeRenderer.ShapeType.Line);
            r.rect(pos.x - dim.x / 2, pos.y - dim.y / 2, dim.x, dim.y);
            r.end();
        }
        hover = false;
        if(info.x - info.fixed_x <= pos.x - dim.x/2)
            return;
        if(info.x - info.fixed_x >= pos.x + dim.x/2)
            return;
        if(h - info.y - info.fixed_y <= pos.y - dim.y/2)
            return;
        if( h - info.y - info.fixed_y >= pos.y + dim.y/2)
            return;
        hover = true;

        if(!visualize)
            return;

    }

}
