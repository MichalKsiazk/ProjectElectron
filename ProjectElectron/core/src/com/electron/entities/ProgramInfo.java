package com.electron.entities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.electron.input.MouseInfo;

public class ProgramInfo {

    public SpriteBatch batch;
    public MouseInfo mouseInfo;
    public ComponentManager componentManager;
    public MainCamera mainCamera;
    public ProgramInfo (){
        batch = new SpriteBatch();
        componentManager = new ComponentManager();
        mouseInfo = new MouseInfo();
        mainCamera = new MainCamera(mouseInfo);
    }
}
