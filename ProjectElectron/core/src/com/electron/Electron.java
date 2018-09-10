package com.electron;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.electron.colliders.Collider2D;
import com.electron.electronics.*;
import com.electron.entities.Entity;
import com.electron.entities.ProgramInfo;
import com.electron.input.MouseInfo;

import java.util.ArrayList;
import java.util.List;

public class Electron extends ApplicationAdapter {
	ProgramInfo info;
	int w,h;



	@Override
	public void create () {
		info = new ProgramInfo();
		w=Gdx.graphics.getWidth();
		h=Gdx.graphics.getHeight();
		Entity ent = new Entity(new Vector2(300,300),0,1,1);
		Switch  s = new Switch(Switch.Type.MONOSTABLE, Switch.State.CLOSED, ent);
		s.tags.add("__TAG__");
		s.name = "__NAME__";
		info.componentManager.components.add(s);
		List<Component> comp;
		comp = info.componentManager.findByTag("__TAG__");
		for(Component c : comp)
			System.out.println(c.name);
		Battery b = new Battery(5,1,500, new Entity(new Vector2(200,200),0,1,1));
		info.componentManager.components.add(b);
	}

	@Override
	public void render () {
		if(Gdx.input.isKeyJustPressed(Input.Keys.E) && !info.componentManager.setting_wire){
			Wire w1 = new Wire(new Entity(new Vector2(0,0), 0, 1, 1));
			info.componentManager.components.add(w1);
		}
		if(Gdx.input.isKeyJustPressed(Input.Keys.W)){
			Entity e = new Entity(new Vector2(info.mouseInfo.x - info.mouseInfo.fixed_x,h - info.mouseInfo.y-info.mouseInfo.fixed_y), 0, 1, 1);
			Switch ss = new Switch(Switch.Type.MONOSTABLE, Switch.State.OPEN, e);
			info.componentManager.components.add(ss);
		}

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		info.mainCamera.Update();
		RenderElectronics();
		info.mouseInfo.Update(info.mainCamera.camera);
	}

	@Override
	public void dispose () {
	}

	void RenderElectronics(){
		for(Component l : info.componentManager.components){
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
		for(Component toAdd : info.componentManager.componentsToAdd){
			info.componentManager.components.add(toAdd);
		}
		info.componentManager.componentsToAdd.clear();
	}
}
