package com.tuwar.calgordi;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.BitmapFontLoader;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import javafx.scene.paint.Color;
import jdk.nashorn.internal.runtime.Debug;
import jdk.nashorn.internal.runtime.RecompilableScriptFunctionData;
import sun.font.TrueTypeFont;


public class TuWarGame extends ApplicationAdapter {
	private SpriteBatch batch; // Mostrar imagenes en buffer

	private Stage stage;
	private AssetManager assets;
	private int ancho;
	private int alto;

	private BitmapFont fuente;
	private Sprite fondoDerechaSprite ;
	private Sprite fondoIzquierdaSprite ;

	private TextField nombreUsuario;
	private TextField contraUsuario;
	private TextField.TextFieldStyle textFieldStyle;
	private Skin skin;

	private CheckBox recordarCuenta;
	private CheckBox.CheckBoxStyle recordarCuentaStyle;


	private TextureAtlas inicioAtlas;
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

		fuente = assets.get("data/fuentesTexto/MaiandraGD.fnt", BitmapFont.class);
		inicioAtlas = assets.get("data/imagenes/inicio/inicio.pack",TextureAtlas.class);

		fondoDerechaSprite = new Sprite(inicioAtlas.findRegion("FondoDerecha"));
		fondoIzquierdaSprite = new Sprite(inicioAtlas.findRegion("FondoIzquierda"));

		stage = new Stage();
		Gdx.input.setInputProcessor(stage);

		//Estilo del textField
		textFieldStyle = new TextField.TextFieldStyle();
		textFieldStyle.font = fuente;
		skin = new Skin();
		skin.addRegions(inicioAtlas);
		textFieldStyle.messageFontColor = new com.badlogic.gdx.graphics.Color(1,1,1,0.5f);
		textFieldStyle.fontColor= new com.badlogic.gdx.graphics.Color(1,1,1,0.6f);
		textFieldStyle.background = skin.getDrawable("FieldText");

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
		fuente.getData().setScale(anchoNU * 0.55f/256);
		textFieldStyle.background.setLeftWidth(anchoNU*2/9 );
		textFieldStyle.background.setBottomHeight(altoNU*2/9);
		textFieldStyle.background.setTopHeight(altoNU*2/9);
		textFieldStyle.background.setRightWidth(anchoNU*2/9);

		//CheckBox style
		recordarCuentaStyle = new CheckBox.CheckBoxStyle();
		recordarCuentaStyle.font = fuente;
		skin = new Skin();
		skin.addRegions(inicioAtlas);
		recordarCuentaStyle.checkboxOff = skin.getDrawable("CheckBox");
		recordarCuentaStyle.checkboxOn = skin.getDrawable("CheckBoxAceptado");

		//CheckBox
		recordarCuenta = new CheckBox("", recordarCuentaStyle);
		recordarCuenta.setBounds(ancho * 53/80, alto *6/16, alto*64/360, alto*64/360);
		stage.addActor(nombreUsuario);
		stage.addActor(contraUsuario);
		stage.addActor(recordarCuenta);
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
