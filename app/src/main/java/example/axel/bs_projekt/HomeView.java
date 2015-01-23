<<<<<<< Updated upstream
package example.axel.bs_projekt;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class HomeView extends Activity implements View.OnClickListener {


    private Button b_user_name, b_newgame, b_highscore, b_multiplayer;
    private FragmentManager fragmentManager;
    private Singleton singleton;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_view);

        singleton = Singleton.GetInstance();

        b_user_name = (Button) findViewById(R.id.b_user_name);
        b_multiplayer = (Button) findViewById(R.id.b_multiplayer);

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/WANNABEME___.otf");

        b_user_name.setText(singleton.getName_user());
        b_user_name.setTypeface(myTypeface);

        b_highscore = (Button) findViewById(R.id.b_highscore);
        b_newgame = (Button) findViewById(R.id.b_newgame);

        b_highscore.setTypeface(myTypeface);
        b_newgame.setTypeface(myTypeface);
        b_multiplayer.setTypeface(myTypeface);


        b_highscore.setOnClickListener(this);
        b_user_name.setOnClickListener(this);
        b_multiplayer.setOnClickListener(this);
        b_newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeView.this, MyActivity.class);
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
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
        }

        if (v == b_user_name) {

            Intent intent = new Intent(HomeView.this, User_Profil.class);
            startActivity(intent);
            overridePendingTransition(R.anim.animation_enter_top_bottom, R.anim.animation_exit_top_bottom);
            finish();
        }

        if (v == b_multiplayer) {

            Intent intent = new Intent(HomeView.this, BluetoothList.class);
            startActivity(intent);

        }


    }
}
=======
package example.axel.bs_projekt;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class HomeView extends Activity implements View.OnClickListener {


    private Button b_user_name, b_newgame, b_highscore, b_multiplayer;
    private FragmentManager fragmentManager;
    private Singleton singleton;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_view);

        singleton = Singleton.GetInstance();

        b_user_name = (Button) findViewById(R.id.b_user_name);
        b_multiplayer = (Button) findViewById(R.id.b_multiplayer);

        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "fonts/WANNABEME___.otf");

        b_user_name.setText(singleton.getName_user());
        b_user_name.setTypeface(myTypeface);

        b_highscore = (Button) findViewById(R.id.b_highscore);
        b_newgame = (Button) findViewById(R.id.b_newgame);

        b_highscore.setTypeface(myTypeface);
        b_newgame.setTypeface(myTypeface);
        b_multiplayer.setTypeface(myTypeface);


        b_highscore.setOnClickListener(this);
        b_user_name.setOnClickListener(this);
        b_newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(HomeView.this, MyActivity.class);
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
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            finish();
        }

        if (v == b_user_name) {

            Intent intent = new Intent(HomeView.this, User_Profil.class);
            startActivity(intent);
            overridePendingTransition(R.anim.animation_enter_top_bottom, R.anim.animation_exit_top_bottom);
            finish();



        }


    }
}
>>>>>>> Stashed changes
