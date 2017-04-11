package utng.edu.mx.menusarahi.login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import utng.edu.mx.menusarahi.MainActivity;

import utng.edu.mx.menusarahi.R;
import utng.edu.mx.menusarahi.gallery.MenuGallery;
import utng.edu.mx.menusarahi.registro.Registro;
import utng.edu.mx.menusarahi.sqlite.DBOperations;
import utng.edu.mx.menusarahi.sqlite.Session;

public class Login extends AppCompatActivity implements View.OnClickListener{
    private EditText etUser;
    private EditText etPassword;
    private Button btIngresar;
    private Button btRegistrar;
    private Cursor cursor;
    private String validName = null;
    private String validPasword =null;
    private String toastMsg = null;
    private ProgressBar progressBar;
    private Session session;
    private DBOperations operations;
    public  static  String id;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        session = new Session(this);
        operations = DBOperations.getDBOperations(this);
        etUser =(EditText)findViewById(R.id.et_user);
        etPassword =(EditText)findViewById(R.id.et_password);
        btIngresar=(Button)findViewById(R.id.bt_ingresar);
        btRegistrar=(Button)findViewById(R.id.bt_sing_up);

        if (session.loggedin()){
            startActivity( new Intent(Login.this,MenuGallery.class));
            finish();
        }

        etUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                 valitUser(etUser);
            }
        });
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                valitPassword(etPassword);

            }
        });


        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,Registro.class));
                finish();
            }
        });
        btIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Boolean resultado =operations.ingresar(Login.this, etUser.getText().toString(), etPassword.getText().toString());

                if ( resultado == true){
                    toastMsg="Bienvenido: "+ etUser.getText().toString();
                    showToast(toastMsg);
                    startActivity(new Intent(Login.this,MenuGallery.class));
                    finish();
                }
                else if (validName!=null&&validPasword!=null){
                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setTitle("Error al iniciar session");
                    builder.setMessage("Usuario |Contraseña Incorrecta");
                    builder.setPositiveButton("Aceptar",null);
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }else if(etUser.getText().toString().equals("")
                        && etPassword.getText().toString().equals("")) {
                    toastMsg="Capos vacios ";
                    showToast(toastMsg);
                } else if(etUser.getText().toString().equals("")
                        || etPassword.getText().toString().equals("")) {
                    toastMsg="Capo vacio ";
                    showToast(toastMsg);
                }else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                    builder.setTitle("Error al iniciar session");
                    builder.setMessage("Usuario |Contraseña Incorrecta");
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                }
                valitUser(etUser);
                valitPassword(etPassword);

            }
        });
    }


    public  void valitUser(EditText edt){
        if (edt.getText().toString().length() <= 0) {
            edt.setError("Campo vacio");
            validName = null;
        } else if (!edt.getText().toString().matches("[a-zA-Z ]+")) {
            edt.setError("solo se asepta el alfaveto");
            validName = null;
        } else {
            validName = edt.getText().toString();
        }
    } public  void valitPassword(EditText edt){
        if (edt.getText().toString().length()<=0){
            edt.setError("Ingresar contraseña");
            validPasword=null;
        }else {
            validPasword=edt.getText().toString();
        }
    }
    public void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    public  void progresBar(){
        progressBar=(ProgressBar)findViewById(R.id.progress_bar);
        progressBar.setProgress(0);

    }

    @Override
    public void onClick(View v) {

    }
}
