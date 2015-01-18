package example.axel.bs_projekt;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Login extends Activity implements View.OnClickListener {

    private SQLiteDatabase db;
    private SQLiteManager sqLiteManager;
    private Cursor cursor;

    private Button button_login;
    private Button button_registration;
    private EditText ed_text_user;
    private EditText ed_text_pass;
    private Singleton singleton;
    private String asd1;
    private int id_username;

    FragmentManager fm;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.activity_login);

        singleton = Singleton.GetInstance();
        fm = getFragmentManager();

        ed_text_user = (EditText) findViewById(R.id.et_user);
        ed_text_pass = (EditText) findViewById(R.id.et_pass);

        ed_text_user.setHintTextColor(Color.BLACK);
        ed_text_pass.setHintTextColor(Color.BLACK);

        button_login = (Button) findViewById(R.id.b_einloggen);
        button_login.setOnClickListener(this);

        button_registration = (Button)findViewById(R.id.b_registration);

        button_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Login.this, Registration_Activity.class);
                startActivity(intent);

            }
        });


    }

    public void onClick(View v) {

        login_ueberpruefung(ed_text_user.getText().toString(), ed_text_pass.getText().toString());

        if(cursor.getCount() == 1) {

            //Bundle bundle = new Bundle();
            Intent intent = new Intent(Login.this, HomeView.class);
            //intent.putExtra("variable", ed_text_user.getText().toString());
            //intent.putExtra("id_username", id_username);
            singleton.setId_user(id_username);
            singleton.setName_user(ed_text_user.getText().toString());
            startActivity(intent);





        } else {
            Toast.makeText(this, "User oder Password ist falsch" , Toast.LENGTH_LONG).show();
        }

    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if(hasFocus) {

            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    public void login_ueberpruefung(String username, String password) {

        sqLiteManager = new SQLiteManager(this);
        db = sqLiteManager.getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM t_user where user='"+ username + "' AND password= '"+ password +"'", null);

        int asd = cursor.getCount();
        asd1 = String.valueOf(asd);

                    if (cursor.moveToFirst()) {
                        do {
                            id_username  = cursor.getInt(cursor.getColumnIndex(SQLiteManager.ID));
                            singleton.setPassword(cursor.getString(cursor.getColumnIndex(SQLiteManager.PASSWORD)));
                            singleton.setEmail(cursor.getString(cursor.getColumnIndex(SQLiteManager.EMAIL)));

                        } while (cursor.moveToNext());
                    }

                    return;
                }

    }



