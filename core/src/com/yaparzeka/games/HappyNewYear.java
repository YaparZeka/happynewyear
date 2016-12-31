package com.yaparzeka.games;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

public class HappyNewYear extends ApplicationAdapter {

	protected Stage stage;
	protected Skin skin;

	protected Table layout;
	protected TextButton newGameBtn;
	protected TextButton quitBtn;

	@Override
	public void create () {
		skin = new Skin(Gdx.files.internal("data/neon-ui.json"));
		stage = new Stage();
		layout = new Table();

		layout.setWidth(stage.getWidth());
		layout.align(Align.center | Align.top);
		layout.setPosition(0, Gdx.graphics.getHeight());

		newGameBtn = new TextButton("New Game",skin);
		newGameBtn.setScale(3);
		quitBtn = new TextButton("Quit Game", skin);

		newGameBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				ServerSocketHints hint = new ServerSocketHints();
				ServerSocket server = Gdx.net.newServerSocket(Net.Protocol.TCP, 1453, hint);
				Gdx.app.log("New Game Butonu","new game butonuna tıkladınız");
				event.stop();
			}
		});
		quitBtn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.log("Quit Butonu","quit butonuna tıkladınız");
				event.stop();
			}
		});

		layout.padTop(80);
		// benim telefonda note3
		layout.add(newGameBtn).padBottom(30).height(200).width(800);
		layout.row(); // yeni satır
		layout.add(quitBtn).height(200).width(800);

		stage.addActor(layout);
		Gdx.input.setInputProcessor(stage); // inputları sahneye yönlendir
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1,1,1,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT); // Boyama yöntemi bit bit boyama

		stage.act();
		stage.draw();
	}
	
	@Override
	public void dispose () {

	}
}