package example.axel.bs_projekt;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;


public class User_Profil extends Activity implements View.OnClickListener {


    private Singleton singleton;
    private TextView email_adress, email_text, password, password_text, username, username_text;
    private Button b_aendern, b_speichern;
    private ImageView profile_picture;
    static int TAKE_PICTURE = 1;
    byte[] byteArray;
    static Uri capturedImageUri=null;
    private ContentValues cv;
    private SQLiteDatabase db;
    private Cursor cursor;
    private SQLiteManager sqLiteManager = new SQLiteManager(this);
    Bitmap image_profile;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profil);

        singleton = Singleton.GetInstance();

        b_aendern = (Button) findViewById(R.id.b_aendern);
        b_speichern = (Button) findViewById(R.id.b_speichern);
        profile_picture = (ImageView) findViewById(R.id.profile_picture_button);

        username = (TextView) findViewById(R.id.benutzername);
        password = (TextView) findViewById(R.id.password);
        email_adress = (TextView) findViewById(R.id.email);

        username_text = (TextView) findViewById(R.id.benutzername_text);
        password_text = (TextView) findViewById(R.id.password_text);
        email_text = (TextView) findViewById(R.id.email_text);


        username.setText(singleton.getName_user());
        password.setText(singleton.getPassword());
        email_adress.setText(singleton.getEmail());

        //Bitmap b1= BitmapFactory.decodeByteArray(singleton.getImage_profile(), 0, singleton.getImage_profile().length);

            //profile_picture.setImageBitmap(b1);

        password.setEnabled(false);
        email_adress.setEnabled(false);

        b_speichern.setVisibility(View.INVISIBLE);

        b_aendern.setOnClickListener(this);
        b_speichern.setOnClickListener(this);
        profile_picture.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {

        if(v == profile_picture) {

            openCamara();

        }

        //verification if button ändern was pressed
        if(v == b_aendern) {
            password.setEnabled(true);
            email_adress.setEnabled(true);
            b_aendern.setVisibility(View.INVISIBLE);
            b_speichern.setVisibility(View.VISIBLE);


        }

        //verification if button speichern was pressed
        if(v == b_speichern) {

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(User_Profil.this);

            // Setting Dialog Title
            alertDialog.setTitle("Confirm Change...");

            // Setting Dialog Message
            alertDialog.setMessage(R.string.Confirmation_Answer);

            // Setting Icon to Dialog
            //alertDialog.setIcon(R.drawable.delete);

            // Setting Positive "Yes" Button
            alertDialog.setPositiveButton("JA", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog,int which) {

                    //verification if password or email was changed
                    if(password.getText().toString() != singleton.getPassword() || email_adress.getText().toString() != singleton.getEmail()) {

                        db = sqLiteManager.getWritableDatabase();
                        //methode update, to change data if the answer true is
                        sqLiteManager.updateProcess(db, singleton.getId_user(), password.getText().toString(), email_adress.getText().toString());


                        password.setEnabled(false);
                        email_adress.setEnabled(false);

                        singleton.setPassword(password.getText().toString());
                        singleton.setEmail(email_adress.getText().toString());

                        b_aendern.setVisibility(View.VISIBLE);
                        b_speichern.setVisibility(View.INVISIBLE);

                    }
                }
            });

            // Setting Negative "NO" Button
            alertDialog.setNegativeButton("NEIN", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    // Write your code here to invoke NO event
                    //Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                    password.setText(singleton.getPassword());
                    email_adress.setText(singleton.getEmail());

                    password.setEnabled(false);
                    email_adress.setEnabled(false);

                    b_aendern.setVisibility(View.VISIBLE);
                    b_speichern.setVisibility(View.INVISIBLE);

                    dialog.cancel();
                }
            });

            // Showing Alert Message
            alertDialog.show();




        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(User_Profil.this, HomeView.class);
        startActivity(intent);
        overridePendingTransition(R.anim.animation_enter_bottom_top, R.anim.animation_exit_bottom_top);
        finish();

    }

    public void openCamara(){
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        // this part to save captured image on provided path
        File file = new File(Environment.getExternalStorageDirectory(),
                "my-photo.jpg");
        capturedImageUri = Uri.fromFile(file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri);

        startActivityForResult(intent, TAKE_PICTURE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == TAKE_PICTURE) {
            try {

                Bitmap photo = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), capturedImageUri);


                ByteArrayOutputStream out = new ByteArrayOutputStream();

                photo.compress(Bitmap.CompressFormat.PNG, 100, out);

                byte[] buffer=out.toByteArray();

                Bitmap b1= BitmapFactory.decodeByteArray(buffer, 0, buffer.length);

                profile_picture.setImageBitmap(b1);

                sqLiteManager.saveImageProcess(db, singleton.getId_user(), buffer);

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

        //image_profile = ((BitmapDrawable)profile_picture.getDrawable()).getBitmap();
        //sqLiteManager.saveImageProcess(db, singleton.getId_user(), image_profile);

    }



}
