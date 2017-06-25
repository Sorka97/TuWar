package com.tuwar.calgordi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by sergi_000 on 25/06/2017.
 */
public class CrearTabla {

    Stage stage = new Stage();;
    Table pantalla;
    float ancho;
    float alto;




    public void create(){
        pantalla = new Table();
        ancho = Gdx.graphics.getWidth();
        alto = Gdx.graphics.getHeight();

        //Tabla parte derecha
        pantalla.setBounds(ancho * 3/5, 0, ancho * 2/5, alto);
        stage.addActor(pantalla);
    }
    public void render(){
        stage.draw();
    }
}