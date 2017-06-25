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
        pantalla.add("").setActorBounds(0,alto *83/90, pantalla.getWidth(), pantalla.getHeight() * 6/90);
        pantalla.add().row();
        pantalla.add("").setActorBounds(0,alto *73/90, pantalla.getWidth() * 9/63, pantalla.getHeight() * 10/90);
        pantalla.add("").setActorBounds(pantalla.getWidth() * 9/63,alto *73/90, pantalla.getWidth() * 47/63, pantalla.getHeight() * 10/90);
        pantalla.add("").setActorBounds(pantalla.getWidth() * 58/63,alto *73/90, pantalla.getWidth() * 7/63, pantalla.getHeight() * 10/90);



        stage.addActor(pantalla);
    }
    public void render(){
        stage.draw();
    }
}
