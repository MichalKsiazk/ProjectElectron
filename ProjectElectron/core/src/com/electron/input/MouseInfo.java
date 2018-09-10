package com.electron.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import javafx.scene.Camera;

public class MouseInfo {
    public int x, y;
    public int fixed_x = 0;
    public int fixed_y = 0;
    public int L_STATE;
    public int R_STATE;
    public int M_STATE;
    public int scrolled;



    public void Update(OrthographicCamera cam){
        x = Gdx.input.getX();
        y = Gdx.input.getY();

        if(L_STATE == 2)
            L_STATE = 1;
        if(L_STATE == 3)
            L_STATE = 0;

        if(M_STATE == 2)
            M_STATE = 1;
        if(M_STATE == 3)
            M_STATE = 0;

        if(R_STATE == 2)
            R_STATE = 1;
        if(R_STATE == 3)
            R_STATE = 0;

        if(M_STATE == 1){
            fixed_x += Gdx.input.getDeltaX();
            fixed_y -= Gdx.input.getDeltaY();
            System.out.println(fixed_y + "  " + fixed_x);
        }


        scrolled = 0;
        Gdx.input.setInputProcessor(new InputAdapter() {
            public boolean touchDown (int x, int y, int pointer, int button){
                if (button == Input.Buttons.LEFT) {
                    L_STATE = 2;
                }
                if (button == Input.Buttons.MIDDLE) {
                    M_STATE = 2;
                }
                if (button == Input.Buttons.RIGHT) {
                    R_STATE = 2;
                }
                return true;
            }



            public boolean touchUp (int x, int y, int pointer, int button){
                if (button == Input.Buttons.LEFT) {
                    L_STATE = 3;
                }
                if (button == Input.Buttons.MIDDLE) {
                    M_STATE = 3;
                }
                if (button == Input.Buttons.RIGHT) {
                    R_STATE = 3;
                }
                return true;
            }

            public boolean scrolled (int amount) {
                scrolled = amount;
                return false;
            }

        });
    }
}
