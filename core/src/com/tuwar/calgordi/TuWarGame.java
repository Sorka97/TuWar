package com.tuwar.calgordi;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;


public class TuWarGame extends ApplicationAdapter {
	//HOLA DAVID
	private SpriteBatch batch; // Cosas que el usuario no interactua
	private Stage stage; // Cosas que el usuario interactua
	private AssetManager assets;

	// Ancho y alto de la pantalla
	private int ancho;
	private int alto;

	// Forma Parte del batch
	private Sprite fondoDerechaSprite ;
	private Sprite fondoIzquierdaSprite ;

	// Forma parte del actor
	private BitmapFont fuenteTB;
	private TextField nombreUsuario;
	private TextField contraUsuario;
	private TextField.TextFieldStyle textFieldStyle;

	private CheckBox recordarCuenta;
	private CheckBox.CheckBoxStyle recordarCuentaStyle;

	private TextButton iniciarSesion;
	private TextButton crearCuenta;
	private TextButton.TextButtonStyle textButtonStyle;


	private BitmapFont fuenteFP;
	private TextButton.TextButtonStyle noSkin;
	private TextButton forgetPass;

	@Override
	public void create () {
		//En la partida añadir ortographic camera para que se renderize segun se mueva el jugador
		batch = new SpriteBatch();

		//Solo cargar los assests a necesitar
		assets = new AssetManager();
		assets.clear();
		assets.load("data/imagenes/inicio/buttonSkinApretado405y120.png", Texture.class);
		assets.load("data/imagenes/inicio/buttonSkin405y120.png", Texture.class);
		assets.load("data/imagenes/inicio/CheckBox64y64.png", Texture.class);
		assets.load("data/imagenes/inicio/CheckBoxAceptado64y64.png", Texture.class);
		assets.load("data/imagenes/inicio/FieldText388y84.png", Texture.class);
		assets.load("data/imagenes/inicio/FondoDerecha512y720.png", Texture.class);
		assets.load("data/imagenes/inicio/FondoIzquierda768y720.png", Texture.class);
		assets.finishLoading();
		//Acabamos de cargarlos

		ancho = Gdx.graphics.getWidth();
		alto = Gdx.graphics.getHeight();

		fuenteTB = new BitmapFont(Gdx.files.internal("data/fuentesTexto/MaiandraGD.fnt")); // no so del mismo tamaño
		fuenteFP = new BitmapFont(Gdx.files.internal("data/fuentesTexto/MaiandraGD.fnt")); // nuevo tamaño


		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		//Estilo del textField
		textFieldStyle = new TextField.TextFieldStyle();
		textFieldStyle.font = fuenteTB;
		textFieldStyle.messageFontColor = new com.badlogic.gdx.graphics.Color(1,1,1,0.5f);
		textFieldStyle.fontColor= new com.badlogic.gdx.graphics.Color(1,1,1,0.6f);
		textFieldStyle.background = new TextureRegionDrawable(new TextureRegion(assets.get("data/imagenes/inicio/FieldText388y84.png", Texture.class)));

		//Texts fields
		nombreUsuario = new TextField("", textFieldStyle);
		nombreUsuario.setMessageText("Nombre usuario");
		nombreUsuario.setBounds(ancho * 13/20, alto *13/16, ancho * 3/10, alto *2/20);
		nombreUsuario.setAlignment(Align.bottomLeft);
		nombreUsuario.setMaxLength(15);

		// TextField Password

		contraUsuario = new TextField("", textFieldStyle);
		contraUsuario.setMessageText("Password");
		contraUsuario.setBounds(ancho * 13/20, alto *11/16, ancho * 3/10, alto *2/20);
		contraUsuario.setAlignment(Align.bottomLeft);
		contraUsuario.setPasswordMode(true);
		contraUsuario.setPasswordCharacter('x');
		contraUsuario.setMaxLength(15);

		//Tamaño de la letra y donde empiezan los inputs (Modificar donde cada uno porq es distinto segun ancho pantalla
		float anchoNU = nombreUsuario.getWidth();



		//CheckBox style
		recordarCuentaStyle = new CheckBox.CheckBoxStyle();
		recordarCuentaStyle.font = fuenteFP;
		recordarCuentaStyle.fontColor = new com.badlogic.gdx.graphics.Color(0.5f,0.5f,1f,0.5f);
		recordarCuentaStyle.checkboxOff = new TextureRegionDrawable(new TextureRegion(assets.get("data/imagenes/inicio/CheckBox64y64.png", Texture.class)));
		recordarCuentaStyle.checkboxOn = new TextureRegionDrawable(new TextureRegion(assets.get("data/imagenes/inicio/CheckBoxAceptado64y64.png", Texture.class)));

		//CheckBox
		recordarCuenta = new CheckBox("Recordar cuenta.", recordarCuentaStyle);
		recordarCuenta.setBounds(ancho * 3/5, alto * 3/16, 10,10);

		// Button style
		textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = fuenteTB;
		textButtonStyle.fontColor= new com.badlogic.gdx.graphics.Color(1,1,1,0.6f);
		textButtonStyle.up = new TextureRegionDrawable(new TextureRegion(assets.get("data/imagenes/inicio/buttonSkin405y120.png", Texture.class)));
		textButtonStyle.down = new TextureRegionDrawable(new TextureRegion(assets.get("data/imagenes/inicio/buttonSkinApretado405y120.png", Texture.class)));

		//Botones
		iniciarSesion = new TextButton("INICIAR SESION", textButtonStyle);
		iniciarSesion.setBounds(ancho * 25/40, alto *3/16, ancho * 7/20, alto *7/40);
		iniciarSesion.align(Align.center);

		crearCuenta = new TextButton("CREAR CUENTA", textButtonStyle);
		crearCuenta.setBounds(ancho * 25/40, alto/50, ancho * 7/20, alto *7/40);
		crearCuenta.align(Align.center);

		//TextStyle del botton para recordar contra
		noSkin = new TextButton.TextButtonStyle();
		noSkin.font = fuenteFP;
		noSkin.fontColor= new com.badlogic.gdx.graphics.Color(0.5f,0.5f,1f,0.6f);
		noSkin.up = null;
		noSkin.down = null;
		noSkin.downFontColor = new com.badlogic.gdx.graphics.Color(0.2f,0.2f,1f,0.6f);

		forgetPass = new TextButton("He olvidado mi password", noSkin);
		forgetPass.setBounds(ancho * 25/40, alto*6/16, anchoNU, alto *1/40);
		forgetPass.align(Align.center);

		fuenteTB.getData().setScale(anchoNU * 0.5f/190);
		fuenteFP.getData().setScale(anchoNU * 0.4f/190);

		stage.addActor(nombreUsuario);
		stage.addActor(contraUsuario);
		stage.addActor(recordarCuenta);
		stage.addActor(iniciarSesion);
		stage.addActor(crearCuenta);
		stage.addActor(forgetPass);
	}



	//la parte derecha son 2/5 de la pantalla y la izquierda 3/5 la altura es la misma que la pantalla
	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(assets.get("data/imagenes/inicio/FondoDerecha512y720.png", Texture.class), ancho * 3 / 5,0,ancho * 2/5, alto);
		batch.draw(assets.get("data/imagenes/inicio/FondoIzquierda768y720.png", Texture.class), 0, 0, ancho * 3/5, alto);
		batch.end();
		stage.draw();
	}

	@Override
	public void dispose () {
		batch.dispose();
	}
}
