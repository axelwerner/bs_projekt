package example.axel.bs_projekt;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;
<<<<<<< Updated upstream


public class MyActivity extends Activity {

    private GameView paintSurface;
    private GameEngine gameEngine;
    private TextView tv_punkte_zaehler;
    Singleton singleton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
=======

>>>>>>> Stashed changes

public class MyActivity extends Activity {

<<<<<<< Updated upstream
=======
    private GameView paintSurface;
    private GameEngine gameEngine;
    private TextView tv_punkte_zaehler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

>>>>>>> Stashed changes

        //Get reference to the GameView
        paintSurface = (GameView) findViewById(R.id.paintSurface);

<<<<<<< Updated upstream
        tv_punkte_zaehler = (TextView) findViewById(R.id.tv_punkte_zaehler);
        tv_punkte_zaehler.setText("" + singleton.getScore());
        //gameEngine.score(tv_punkte_zaehler = (TextView) findViewById(R.id.tv_punkte_zaehler));

        //Set OnTouchListener
        paintSurface.setOnTouchListener(paintSurface);
=======

        //gameEngine.score(tv_punkte_zaehler = (TextView) findViewById(R.id.tv_punkte_zaehler));
>>>>>>> Stashed changes

        //Set OnTouchListener
        paintSurface.setOnTouchListener(paintSurface);

<<<<<<< Updated upstream
=======

>>>>>>> Stashed changes

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
