package com.tuwar.calgordi;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;


public class TuWarGame extends ApplicationAdapter {
	private SpriteBatch batch; // Mostrar imagenes en buffer

	private Stage stage;
	private AssetManager assets;
	private int ancho;
	private int alto;

	private Sprite fondoDerechaSprite ;
	private Sprite fondoIzquierdaSprite ;

	private BitmapFont fuenteTB;
	private TextField nombreUsuario;
	private TextField contraUsuario;
	private TextField.TextFieldStyle textFieldStyle;
	private Skin skinTF;

	private BitmapFont fuenteCB;
	private CheckBox recordarCuenta;
	private CheckBox.CheckBoxStyle recordarCuentaStyle;
	private Skin skinCB;

	private TextButton iniciarSesion;
	private TextButton crearCuenta;
	private TextButton.TextButtonStyle textButtonStyle;
	private TextureAtlas inicioAtlas;
	private Skin skinTB;

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
		assets.load("data/imagenes/inicio/inicio.pack", TextureAtlas.class);
		assets.load("data/fuentesTexto/MaiandraGD.fnt", BitmapFont.class);
		assets.finishLoading();
		//Acabamos de cargarlos

		ancho = Gdx.graphics.getWidth();
		alto = Gdx.graphics.getHeight();

		BitmapFont fuente;
		fuente = assets.get("data/fuentesTexto/MaiandraGD.fnt", BitmapFont.class);

		fuenteTB = fuente;
		fuenteCB = fuente;
		fuenteFP= fuente;
		inicioAtlas = assets.get("data/imagenes/inicio/inicio.pack",TextureAtlas.class);

		fondoDerechaSprite = new Sprite(inicioAtlas.findRegion("FondoDerecha"));
		fondoIzquierdaSprite = new Sprite(inicioAtlas.findRegion("FondoIzquierda"));

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		//Estilo del textField
		textFieldStyle = new TextField.TextFieldStyle();
		textFieldStyle.font = fuenteFP;
		skinTF = new Skin();
		skinTF.addRegions(inicioAtlas);
		textFieldStyle.messageFontColor = new com.badlogic.gdx.graphics.Color(1,1,1,0.5f);
		textFieldStyle.fontColor= new com.badlogic.gdx.graphics.Color(1,1,1,0.6f);
		textFieldStyle.background = skinTF.getDrawable("FieldText");

		//Texts fields
		nombreUsuario = new TextField("", textFieldStyle);
		nombreUsuario.setMessageText("Nombre usuario");
		nombreUsuario.setBounds(ancho * 3/5, alto *11/16, ancho * 2/5, alto / 5);
		nombreUsuario.setAlignment(Align.bottomLeft);
		nombreUsuario.setMaxLength(15);

		// TextField Password

		contraUsuario = new TextField("", textFieldStyle);
		contraUsuario.setMessageText("Contraseña");
		contraUsuario.setBounds(ancho * 3/5, alto *8/16, ancho * 2/5, alto /5);
		contraUsuario.setAlignment(Align.bottomLeft);
		contraUsuario.setPasswordMode(true);
		contraUsuario.setPasswordCharacter('x');
		contraUsuario.setMaxLength(15);

		//Tamaño de la letra y donde empiezan los inputs (Modificar donde cada uno porq es distinto segun ancho pantalla
		float anchoNU = nombreUsuario.getWidth();
		float altoNU = nombreUsuario.getHeight();
		textFieldStyle.font.getData().setScale(anchoNU * 0.55f/256);
		textFieldStyle.background.setLeftWidth(anchoNU*2/9 );
		textFieldStyle.background.setBottomHeight(altoNU*2/9);
		textFieldStyle.background.setTopHeight(altoNU*2/9);
		textFieldStyle.background.setRightWidth(anchoNU*2/9);

		//CheckBox style
		recordarCuentaStyle = new CheckBox.CheckBoxStyle();
		recordarCuentaStyle.font = fuenteCB;
		skinCB = new Skin();
		skinCB.addRegions(inicioAtlas);
		recordarCuentaStyle.fontColor = new com.badlogic.gdx.graphics.Color(0.5f,0.5f,1f,0.5f);
		recordarCuentaStyle.checkboxOff = skinCB.getDrawable("CheckBox");
		recordarCuentaStyle.checkboxOn = skinCB.getDrawable("CheckBoxAceptado");

		//CheckBox111111
		recordarCuenta = new CheckBox("Recordar usuario.", recordarCuentaStyle);
		recordarCuenta.setPosition(ancho * 3/5, alto *5/16);

		// Button style
		textButtonStyle = new TextButton.TextButtonStyle();
		textButtonStyle.font = fuenteTB;
		skinTB = new Skin();
		skinTB.addRegions(inicioAtlas);
		textButtonStyle.fontColor= new com.badlogic.gdx.graphics.Color(1,1,1,0.6f);
		textButtonStyle.up = skinTB.getDrawable("buttonSkin");
		textButtonStyle.down = skinTB.getDrawable("buttonSkin2");

		//Botones
		iniciarSesion = new TextButton("INICIAR SESION", textButtonStyle);
		iniciarSesion.setBounds(ancho * 3/5, alto *3/16, ancho * 2/5, alto / 5);
		iniciarSesion.align(Align.center);

		crearCuenta = new TextButton("CREAR CUENTA", textButtonStyle);
		crearCuenta.setBounds(ancho * 3/5, alto /100, ancho * 2/5, alto / 5);
		crearCuenta.align(Align.center);

		//TextStyle del botton para recordar contra
		noSkin = new TextButton.TextButtonStyle();
		noSkin.font = fuenteFP;
		noSkin.fontColor= new com.badlogic.gdx.graphics.Color(0.5f,0.5f,1f,0.6f);
		noSkin.up = null;
		noSkin.down = null;
		noSkin.downFontColor = new com.badlogic.gdx.graphics.Color(0.2f,0.2f,1f,0.6f);

		forgetPass = new TextButton("He olvidado mi password", noSkin);
		forgetPass.setBounds(ancho * 3/5, alto *5/16, ancho * 2/5, alto / 10);
		forgetPass.align(Align.center);

		fuenteTB.getData().setScale(anchoNU * 0.5f/256);
		fuenteCB.getData().setScale(anchoNU * 0.3f/256);
		fuenteFP.getData().setScale(anchoNU * 0.5f/256);


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
		batch.draw(fondoDerechaSprite, ancho * 3 / 5,0,ancho * 2/5, alto);
		batch.draw(fondoIzquierdaSprite, 0, 0, ancho * 3/5, alto);
		batch.end();
		stage.draw();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
