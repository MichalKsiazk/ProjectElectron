package com.electron.entities;

import com.badlogic.gdx.math.Vector2;

public class Entity {
    public Vector2 position;
    public float rotation;
    public float scale;
    public int width;
    public Entity(Vector2 position, float rotation, float scale, int width) {
        SetAll(position, rotation, scale, width);
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public void setWidth(int width) {
        this.width = width;
    }
    public void SetAll(Vector2 position, float rotation, float scale, int width){
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
        this.width = width;
    }
    public Entity(){
        this.position = new Vector2(0,0);
        this.rotation = 0;
        this.scale = 1;
        this.width = 1;
    }
}
