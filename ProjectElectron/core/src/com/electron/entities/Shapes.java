package com.electron.entities;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

public class Shapes {

    public static void light(Camera cam, Vector2 pos, float angle, Color c, float scale) {
        ShapeRenderer r = new ShapeRenderer();
        r.translate(pos.x, pos.y, 0f);
        r.rotate(0, 0, 1, angle);
        r.setProjectionMatrix(cam.combined);
        r.begin(ShapeRenderer.ShapeType.Line);
        //r.setColor(c.r, c.g, c.b, c.a);
        r.setColor(c);
        float dst = (float)Math.sqrt(scale * scale / 2);
        r.line(new Vector2(-dst, -dst), new Vector2(dst, dst));
        r.line(new Vector2(-dst, dst), new Vector2(dst, -dst));
        r.circle(0, 0, scale);
        r.flush();
        r.end();

    }
    public static void wire(Camera cam, Vector2 start, Vector2 stop, Color c, float w) {
        ShapeRenderer r = new ShapeRenderer();
        r.setProjectionMatrix(cam.combined);
        r.begin(ShapeRenderer.ShapeType.Filled);
        r.setColor(c);
        r.rectLine(start.x, start.y,stop.x, start.y,w);
        //r.translate(start.x, stop.y, 0);
        r.rectLine(stop.x, start.y, stop.x, stop.y,w);
        r.flush();
        r.end();
    }
    public static void resistor(Camera cam, Batch batch, Vector2 pos, Color c, float angle, String value){

        ShapeRenderer r = new ShapeRenderer();
        r.setProjectionMatrix(cam.combined);
        r.translate(0, 0, 0);
        r.translate(pos.x, pos.y, 0);
        r.rotate(0, 0, 1, angle);
        r.begin(ShapeRenderer.ShapeType.Line);
        r.setColor(c);
        r.line(-70, 0,-50, 0);
        r.rect(-50,-20,100,40);
        r.line(70, 0,50, 0);
        r.flush();
        r.end();
        batch.begin();
        BitmapFont font = new BitmapFont();
        font.setColor(c);
        font.draw(batch, value, pos.x, pos.y);
        batch.end();
    }
    public static void com(Camera cam, Vector2 pos, Color c){
        ShapeRenderer r = new ShapeRenderer();
        r.setProjectionMatrix(cam.combined);
        r.begin(ShapeRenderer.ShapeType.Line);
        r.setColor(c);
        r.translate(pos.x, pos.y, 0);
        r.line(0, 30, 0, 0);
        r.line(-28, 0, 28, 0);
        r.line(-18, -12, 18, -12);
        r.line(-8, -24, 8, -24);
        r.flush();
        r.end();
    }
}
