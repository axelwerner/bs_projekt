package example.axel.bs_projekt;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

import static android.view.View.OnClickListener;


public class Game_View2 extends Activity implements OnClickListener {

    public static final int PROGRESS_DURATION = 30;
    Button button_minus, button_plus;
    TextView textView_punkte_start, textView_erreichen, textview_finish, textview_punkteanzahl;
    ProgressBar progressBar;
    Singleton singleton;

    Random rnd;
    int punkte = 0;
    int start_nummer;
    int nummer_erreichen;
    int bar_i = 30;
    int letzte_punkte = 0;

    FragmentManager fragmentManager;
    SQLiteManager sqLiteManager;
    ContentValues cv;
    SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game__view2);

        singleton = Singleton.GetInstance();
        fragmentManager = getFragmentManager();


        textView_punkte_start = (TextView) findViewById(R.id.tv_nummer_start);
        textView_erreichen = (TextView) findViewById(R.id.tv_nummer_erreichen);
        textview_finish = (TextView) findViewById(R.id.tv_finish);
        textview_punkteanzahl = (TextView) findViewById(R.id.tv_punkteanzahl);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        button_minus = (Button) findViewById(R.id.button_minus);
        button_plus = (Button) findViewById(R.id.button_plus);

        textview_punkteanzahl.setText(Integer.toString(punkte));
        button_minus.setText("----");
        button_plus.setText("++++");
        button_minus.setClickable(false);
        button_plus.setClickable(false);

        progressBar.setMax(PROGRESS_DURATION);
        progressBar.setMinimumHeight(5);
        progressBar.setProgress(bar_i);



        textView_punkte_start.setTextSize(40);
        textView_erreichen.setTextSize(50);
        textview_punkteanzahl.setTextSize(50);




        button_minus.setOnClickListener(this);
        button_plus.setOnClickListener(this);


        spielstarten();

        final FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        Go_countdown go_countdown = new Go_countdown();
        //Bundle bundle = new Bundle();
        //bundle.putInt("test", letzte_punkte);
        //go_countdown.setArguments(bundle);
        fragmentTransaction.replace(R.id.relative_layout_game, go_countdown);
        fragmentTransaction.commit();

        long minute = 1000*3;//convert millescond to seconds
        long close_minutes = minute--;//set for 3 seconds
        Log.v("Log_tag", "" + close_minutes);
        final Handler handler = new Handler();
        Runnable r=new Runnable() {
            @Override
            public void run() {
                //do something count down is finished

                new Thread(new Runnable() {
                    @Override
                    public void run() {


                        for (int i = PROGRESS_DURATION; i > -1; i--) {


                            Log.v("Log_tag", "Tick of Progress" + i);

                            progressBar.setProgress(i);

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }


                        handler.post(new Runnable() {
                            @Override
                            public void run() {

                                Save_punkte_Process();

                                final FragmentTransaction fragmentTransaction =
                                        fragmentManager.beginTransaction();
                                Game_over_fragment go_fragment = new Game_over_fragment();
                                Bundle bundle = new Bundle();
                                bundle.putInt("test", letzte_punkte);
                                go_fragment.setArguments(bundle);
                                fragmentTransaction.replace(R.id.relative_layout_game, go_fragment);
                                fragmentTransaction.commit();
                            }
                        });
                    }

                }).start();

            }

        };
        handler.postDelayed(r,close_minutes);
    }



    @Override
    public void onClick(View v) {

        if (v == button_minus) {



            start_nummer--;
            textView_punkte_start.setText(Integer.toString(start_nummer));

            if (start_nummer == 11) {
                start_nummer = 0;
                textView_punkte_start.setText(Integer.toString(start_nummer));
            }

            if (start_nummer == -1) {
                start_nummer = 10;
                textView_punkte_start.setText(Integer.toString(start_nummer));
            }

            if (start_nummer == nummer_erreichen) {

                textview_finish.setText("Sie haben gewonnen");
                spielstarten();
                this.punkte++;
                int zwischen_punkte = this.punkte;
                letzte_punkte = zwischen_punkte;
                textview_punkteanzahl.setText(Integer.toString(letzte_punkte));
                Animation a = AnimationUtils.loadAnimation(Game_View2.this, R.anim.scaleanimation_text);
                a.reset();
                textview_punkteanzahl.clearAnimation();
                textview_punkteanzahl.startAnimation(a);


            }

        }

        if (v == button_plus) {
            start_nummer++;
            textView_punkte_start.setText(Integer.toString(start_nummer));

            if (start_nummer == 11) {
                start_nummer = 0;
                textView_punkte_start.setText(Integer.toString(start_nummer));
            }

            if (start_nummer == -1) {
                start_nummer = 10;
                textView_punkte_start.setText(Integer.toString(start_nummer));
            }

            if (start_nummer == nummer_erreichen) {

                textview_finish.setText("Sie haben gewonnen");
                spielstarten();
                this.punkte++;
                int zwischen_punkte = this.punkte;
                letzte_punkte = zwischen_punkte;
                textview_punkteanzahl.setText(Integer.toString(letzte_punkte));
                Animation a = AnimationUtils.loadAnimation(Game_View2.this, R.anim.scaleanimation_text);
                a.reset();
                textview_punkteanzahl.clearAnimation();
                textview_punkteanzahl.startAnimation(a);

            }

        }


    }

    public void spielstarten() {

        rnd = new Random();
        start_nummer = rnd.nextInt(10);
        nummer_erreichen = rnd.nextInt(10);

        textview_finish.setText("");


        textView_punkte_start.setText(Integer.toString(start_nummer));
        textView_erreichen.setText(Integer.toString(nummer_erreichen));

    }

    public void Save_punkte_Process() {


        sqLiteManager = new SQLiteManager(this);
        db = sqLiteManager.getWritableDatabase();

        cv = new ContentValues();

        cv.put(SQLiteManager.ID_NAME, singleton.getId_user());
        cv.put(SQLiteManager.PUNKTE, letzte_punkte);

        db.insert(SQLiteManager.TABLE_PUNKTE , null, cv);
        db.close();


    }


}
