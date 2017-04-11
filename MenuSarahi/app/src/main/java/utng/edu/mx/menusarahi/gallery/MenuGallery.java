package utng.edu.mx.menusarahi.gallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import utng.edu.mx.menusarahi.R;

public class MenuGallery extends AppCompatActivity implements View.OnClickListener {
    private ImageView ivAbecedario;
    private ImageView ivFrutas;
    private ImageView ivAnimals;
    private ImageView ivSaxofon;
    private ImageView ivChelo;
    private ImageView ivBateria;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_menu_gallery);
        ivAbecedario = (ImageView)findViewById(R.id.iv_abecedario);
        ivFrutas =(ImageView)findViewById(R.id.iv_frutas);
        ivAnimals =(ImageView)findViewById(R.id.iv_animals);
        ivSaxofon =(ImageView)findViewById(R.id.iv_saxofon);
        ivChelo =(ImageView)findViewById(R.id.iv_chelo);
        ivBateria = (ImageView) findViewById(R.id.iv_bateria);

        ivAbecedario.setOnClickListener(this);
        ivFrutas.setOnClickListener(this);
        ivAnimals.setOnClickListener(this);
        ivSaxofon.setOnClickListener(this);
        ivChelo.setOnClickListener(this);
        ivBateria.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_abecedario:
                startIntent(BaseGuitarras.class);
                break;
            case R.id.iv_frutas:
                startIntent(BasePiano.class);
                break;
            case R.id.iv_animals:
                startIntent(BasePiano.class);

                break;
            case R.id.iv_saxofon:
                startIntent(BaseSaxofon.class);
                break;
            case R.id.iv_chelo:
                startIntent(BaseChelo.class);
                break;
            case R.id.iv_bateria:
                startIntent(BaseBateria.class);
                break;
        }
    }



    public void startIntent( Class c){
        Intent intent = new Intent(this,c);
        startActivity(intent);
    }


}
