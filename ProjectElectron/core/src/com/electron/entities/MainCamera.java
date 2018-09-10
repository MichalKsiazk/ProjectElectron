package com.electron.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.electron.input.MouseInfo;

public class MainCamera {

    public OrthographicCamera camera;
    MouseInfo mouseInfo;
    boolean move_cam = false;

    public MainCamera(MouseInfo mouseInfo) {
        this.mouseInfo = mouseInfo;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void Update(){
        MoveCamera();
    }
    public void MoveCamera() {
        //System.out.println(mouseInfo.M_STATE);
        if(mouseInfo.M_STATE == 1)
            camera.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
        camera.update();
    }
    public void ScaleCamera() {
        float max = 0.5f;
        float min = 1.0f;
        float scrolled = (float) mouseInfo.scrolled/50;
        //System.out.println(scrolled + "   " + camera.zoom);
        if(camera.zoom + scrolled >= max && camera.zoom + scrolled <= min)
            camera.zoom += scrolled;


    }
}
