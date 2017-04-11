package utng.edu.mx.menusarahi.registro;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import utng.edu.mx.menusarahi.R;
import utng.edu.mx.menusarahi.login.Login;
import utng.edu.mx.menusarahi.model.User;
import utng.edu.mx.menusarahi.sqlite.DBOperations;

public class Registro extends AppCompatActivity {

    private EditText etUser;
    private EditText etPassword;
    private EditText etFirstname;
    private EditText etLastname;
    private Button btAddUser;
    private Button btCancel;
    private DBOperations operations;
    private User user;
    private String toastMsg;
    private String valitUser= null;
    private String valitPassword= null;
    private String valitFirstname= null;
    private String valitLastname = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_registro);
        operations =DBOperations.getDBOperations(getApplicationContext());
        initComponents();
        user = new User();



    }
    private void initComponents(){
        etUser =(EditText)findViewById(R.id.et_user);
        etPassword =(EditText)findViewById(R.id.et_password);
        etFirstname =(EditText)findViewById(R.id.et_firstname);
        etLastname =(EditText)findViewById(R.id.et_lastname);
        btAddUser =(Button)findViewById(R.id.bt_add_user);
        btCancel =(Button)findViewById(R.id.bt_cancel);
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
        etFirstname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                valitFirsname(etFirstname);
            }
        });
        etLastname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                valitLastname(etLastname);
            }
        });
        btCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registro.this,Login.class));
                finish();
            }
        });

        btAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferencias=getSharedPreferences("datos", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=preferencias.edit();
                editor.putString("nombre", etFirstname.getText().toString()+" "+etLastname.getText().toString());
                editor.commit();
                valitUser(etUser);
                valitPassword(etPassword);
                valitFirsname(etFirstname);
                valitLastname(etLastname);


                if (valitUser != null
                        && valitPassword != null
                        && valitFirstname != null
                        && valitLastname != null
                        && valitUser.length() != 0
                        && valitPassword.length() != 0
                        && valitFirstname.length() != 0
                        && valitLastname.length() != 0
                        ) {
                    operations.addUser(user = new User("",
                            valitUser,
                            valitPassword,
                            valitFirstname,
                            valitLastname));
                    startActivity(new Intent(Registro.this,Login.class));
                    finish();
                    showToast("Usuario Registrado");
                }else {
                    toastMsg="error";
                    showToast(toastMsg);
                }

            }
        });
    }

    public  void valitUser(EditText edt) {
        if (edt.getText().toString().length() <= 0) {
            edt.setError("Campo vacio");
            valitUser = null;
        }else {
            valitUser = edt.getText().toString();
        }
    }
    public  void valitPassword(EditText edt) {
        if (edt.getText().toString().length() <= 0) {
            edt.setError("Campo vacio");
            valitPassword = null;
        } else {
            valitPassword = edt.getText().toString();
        }
    }

    public  void valitFirsname(EditText edt) {
        if (edt.getText().toString().length() <= 0) {
            edt.setError("Campo vacio");
            valitFirstname = null;
        } else if (!edt.getText().toString().matches("[a-zA-Z ]+")) {
            edt.setError("solo se asepta el alfaveto");
            valitFirstname = null;
        } else {
            valitFirstname = edt.getText().toString();
        }
    }
    public  void valitLastname(EditText edt){
        if (edt.getText().toString().length() <= 0) {
            edt.setError("Campo vacio");
            valitLastname = null;
        } else if (!edt.getText().toString().matches("[a-zA-Z ]+")) {
            edt.setError("solo se asepta el alfaveto");
            valitLastname = null;
        } else {
            valitLastname = edt.getText().toString();
        }
    }
    public void showToast(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }
}
