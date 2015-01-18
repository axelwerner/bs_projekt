package example.axel.bs_projekt;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FragmentLogin extends Fragment implements View.OnClickListener {

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
    private FragmentManager fm;
    private FragmentTransaction ft;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_login, container, false);

        singleton = Singleton.GetInstance();
        fm = getFragmentManager();

        ed_text_user = (EditText) view.findViewById(R.id.et_user);
        ed_text_pass = (EditText) view.findViewById(R.id.et_pass);

        ed_text_user.setHintTextColor(Color.BLACK);
        ed_text_pass.setHintTextColor(Color.BLACK);

        button_login = (Button) view.findViewById(R.id.b_einloggen);
        button_login.setOnClickListener(this);

        button_registration = (Button) view.findViewById(R.id.b_registration);

        button_registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ft = getActivity().getFragmentManager().beginTransaction();
                ft.setCustomAnimations(R.anim.slide_up, R.anim.slide_down);
                FragmentRegistration fragmentRegistration = new FragmentRegistration();
                ft.replace(R.id.loginLayout, fragmentRegistration);
                ft.commit();

                //Intent intent = new Intent(getActivity(), Registration_Activity.class);
                //startActivity(intent);

            }
        });


        return view;
    }


    public void onClick(View v) {

        login_ueberpruefung(ed_text_user.getText().toString(), ed_text_pass.getText().toString());

        if(cursor.getCount() == 1) {

            Intent intent = new Intent(getActivity().getApplication() , HomeView.class);
            singleton.setId_user(id_username);
            singleton.setName_user(ed_text_user.getText().toString());
            startActivity(intent);

        } else {
            Toast.makeText(getActivity(), "User oder Password ist falsch", Toast.LENGTH_LONG).show();
        }

    }




    public void login_ueberpruefung(String username, String password) {

        sqLiteManager = new SQLiteManager(getActivity());
        db = sqLiteManager.getReadableDatabase();

        cursor = db.rawQuery("SELECT * FROM t_user where user='"+ username + "' AND password= '"+ password +"'", null);

        int asd = cursor.getCount();
        asd1 = String.valueOf(asd);

        if (cursor.moveToFirst()) {
            do {
                id_username  = cursor.getInt(cursor.getColumnIndex(SQLiteManager.ID));
                singleton.setPassword(cursor.getString(cursor.getColumnIndex(SQLiteManager.PASSWORD)));
                singleton.setEmail(cursor.getString(cursor.getColumnIndex(SQLiteManager.EMAIL)));
                singleton.setImage_profile(cursor.getBlob(cursor.getColumnIndex(SQLiteManager.IMAGE)));

           } while (cursor.moveToNext());
        }

        return;
    }

}
