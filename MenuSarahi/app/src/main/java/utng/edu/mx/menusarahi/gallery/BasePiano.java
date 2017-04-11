package utng.edu.mx.menusarahi.gallery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import utng.edu.mx.menusarahi.R;

public class BasePiano extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_base_piano);



    }
}
