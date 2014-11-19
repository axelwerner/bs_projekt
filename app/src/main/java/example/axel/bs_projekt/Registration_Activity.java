package example.axel.bs_projekt;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.*;


public class Registration_Activity extends Activity implements View.OnClickListener{

    EditText ed_text_user;
    EditText ed_text_pass;
    EditText ed_text_pass_2;

    ContentValues cv;
    SQLiteDatabase db;

    String asd1;

    Button button_conf_registration;
    Cursor cursor;
    SQLiteManager sqLiteManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        ed_text_user = (EditText) findViewById(R.id.ed_reg_user);
        ed_text_pass = (EditText) findViewById(R.id.ed_reg_pass);
        ed_text_pass_2 = (EditText) findViewById(R.id.ed_reg_pass2);

        button_conf_registration = (Button) findViewById(R.id.b_conf_registration);



        button_conf_registration.setOnClickListener(this);


    }

    public void onClick(View v) {

        Intent intent = new Intent(Registration_Activity.this, MyActivity.class);
        username_ueberpruefung(ed_text_user.getText().toString());
        if(ed_text_pass.getText().toString().equals(ed_text_pass_2.getText().toString()) ) {
            if (cursor.getCount() == 0) {

                SaveProcess();
                startActivity(intent);

            } else {

                Toast.makeText(this, "Username ist schon vorhanden", Toast.LENGTH_SHORT).show();

            }
        } else {
            Toast.makeText(this, "Passwörter sind nicht gleich", Toast.LENGTH_SHORT).show();

        }

    }

    public void SaveProcess() {

        sqLiteManager = new SQLiteManager(this);
        db = sqLiteManager.getWritableDatabase();

        cv = new ContentValues();


        cv.put(SQLiteManager.USERNAME, ed_text_user.getText().toString());
        cv.put(SQLiteManager.PASSWORD, ed_text_pass.getText().toString());

        db.insert(SQLiteManager.TABLE_USER  , null, cv);
        db.close();


    }

    public void username_ueberpruefung(String username) {

        sqLiteManager = new SQLiteManager(this);
        db = sqLiteManager.getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM t_user where user='"+ username + "'", null);

        int asd = cursor.getCount();
        asd1 = String.valueOf(asd);



    }


}
