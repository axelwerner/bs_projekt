package example.axel.bs_projekt;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;


public class MyActivity extends Activity {

    private GameView paintSurface;
    private GameEngine gameEngine;
    private TextView tv_punkte_zaehler;
    Singleton singleton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

        singleton = Singleton.GetInstance();


        //Get reference to the GameView
        paintSurface = (GameView) findViewById(R.id.paintSurface);

        tv_punkte_zaehler = (TextView) findViewById(R.id.tv_punkte_zaehler);
        tv_punkte_zaehler.setText("" + singleton.getScore());
        //gameEngine.score(tv_punkte_zaehler = (TextView) findViewById(R.id.tv_punkte_zaehler));

        //Set OnTouchListener
        paintSurface.setOnTouchListener(paintSurface);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }
    @Override
    protected void onResume (){

        super.onResume();

        paintSurface.resume();
    }

    @Override
    protected void onPause (){

        super.onPause();

        paintSurface.pause();
    }


}
