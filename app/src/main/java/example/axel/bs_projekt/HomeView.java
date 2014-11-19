package example.axel.bs_projekt;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class HomeView extends Activity implements View.OnClickListener {


    Button b_user_name;
    Button b_highscore;
    Button b_newgame;
    FragmentManager fragmentManager;
    Singleton singleton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_view);

        singleton = Singleton.GetInstance();

        b_user_name = (Button) findViewById(R.id.b_user_name);


        //final Bundle bundle_user_name = getIntent().getExtras();
        //final String user_name = bundle_user_name.getString("variable");
        //final int id_username = bundle_user_name.getInt("id_username");

        b_user_name.setText(singleton.getName_user() + " " + singleton.getId_user());

        b_highscore = (Button) findViewById(R.id.b_highscore);
        b_newgame = (Button) findViewById(R.id.b_newgame);



        b_highscore.setOnClickListener(this);
        b_user_name.setOnClickListener(this);
        b_newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeView.this, Game_View2.class);
                //Bundle bundle_user_name_2 = new Bundle();
                //bundle_user_name_2.putInt("username_id", id_username);
                //intent.putExtras(bundle_user_name_2);
                startActivity(intent);

            }

        });




    }


    @Override
    public void onClick(View v) {

        if (v == b_highscore) {

            Intent intent = new Intent(HomeView.this, Highscores_Activity.class);
            startActivity(intent);
        }

        if (v == b_user_name) {

            final FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            User_Profil user_profil = new User_Profil();
            fragmentTransaction.replace(R.id.linear_layout_home, user_profil);
            fragmentTransaction.commit();


        }


    }
}
