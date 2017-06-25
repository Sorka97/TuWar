package com.tuwar.calgordi;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by sergi_000 on 25/06/2017.
 */
public class CrearTabla {

    Table pantalla;
    float ancho;
    float alto;




    public void create(){
        pantalla = new Table();
        ancho = Gdx.graphics.getWidth();
        alto = Gdx.graphics.getHeight();

        pantalla.setBounds(0,0, ancho, alto);
        //Distribucion en las dos partes
        pantalla.add("parteIzquierda").setActorBounds(0,0,ancho * 3/5, alto);
        pantalla.add("parteDerecha").setActorBounds(ancho * 3/5, 0, ancho * 2/5, alto);


    }
}
