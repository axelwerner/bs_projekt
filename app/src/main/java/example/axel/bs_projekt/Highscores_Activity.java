package example.axel.bs_projekt;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.Touch;
import android.view.Display;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import org.w3c.dom.Text;

import java.security.Key;
import java.util.ArrayList;


public class Highscores_Activity extends Activity implements AdapterView.OnItemClickListener, GestureDetector.OnGestureListener {

    SQLiteDatabase db;
    SQLiteManager sqLiteManager;
    ArrayList<String> highscore_liste;
    ListView lv_highscore;
    String[] anzeigeSpalten;
    GestureDetector gestureDetector;
    ViewFlipper flipper;

    Singleton singleton;

    Animation slide_out_left, slide_in_left, slide_out_right, slide_in_right;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscores);


        singleton = Singleton.GetInstance();

        gestureDetector = new GestureDetector(this,this);
        sqLiteManager = new SQLiteManager(this);
        db = sqLiteManager.getReadableDatabase();
        lv_highscore = (ListView) findViewById(R.id.lv_highscore);
        flipper = (ViewFlipper) findViewById(R.id.flippper);

        slide_in_left = AnimationUtils.loadAnimation(this, R.anim.slide_in_left);
        slide_out_left = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);
        slide_in_right = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        slide_out_right = AnimationUtils.loadAnimation(this, R.anim.slide_out_right);


        createListViewAll();
        lv_highscore.setOnItemClickListener(this);

        flipper.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(gestureDetector.onTouchEvent(event)) {
                    return false;
                }
                return true;

            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        Animation animation = AnimationUtils.loadAnimation(Highscores_Activity.this, R.anim.animationlistviewitem);
        view.startAnimation(animation);



    }

    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                           float velocityY) {
        float sensitvity = 50;

        if ((e1.getX() - e2.getX()) > sensitvity) {
            flipper.setOutAnimation(slide_out_left);
            flipper.setInAnimation(slide_in_right);
            if(flipper.getDisplayedChild() == 0) {
                createListViewUser(singleton.getId_user());
            } else {
                createListViewAll();
            }
            Toast.makeText(this, "" + flipper.getDisplayedChild() + "", Toast.LENGTH_LONG).show();
            flipper.showNext();

        } else if ((e2.getX() - e1.getX()) > sensitvity) {
            flipper.setOutAnimation(slide_out_right);
            flipper.setInAnimation(slide_in_left);
            if(flipper.getDisplayedChild() == 0) {
                createListViewUser(singleton.getId_user());
            } else {
                createListViewAll();
            }
            flipper.showPrevious();

        }

        return true;
    }

    public void createListViewAll() {

        Cursor cursor = db.rawQuery("SELECT * FROM t_punkte INNER JOIN t_user ON t_punkte.id_name=t_user._id ORDER BY punkte DESC", null);

        anzeigeSpalten = new String[] { SQLiteManager.ID ,  SQLiteManager.USERNAME, SQLiteManager.PUNKTE, SQLiteManager.DATUM};

        int[] anzeigeViews = new int[] { R.id.tv_id_user , R.id.tv_username_highscore, R.id.tv_punkte_highscore, R.id.tv_datum};

        MyCursorAdapter adapter = new MyCursorAdapter(this, R.layout.listen_layout ,cursor , anzeigeSpalten, anzeigeViews, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        int asd = adapter.getCount();
        String asd1 = String.valueOf(asd);

        for(int i=0; i < adapter.getCount(); i++) {


            if((i % 2) == 0) {

            } else {

            }
        }

        lv_highscore.setAdapter(adapter) ;
    }

    public void createListViewUser(int user_id) {

        //Cursor cursor = db.rawQuery("SELECT * FROM t_user ORDER BY punkte DESC", null);
        Cursor cursor = db.rawQuery("SELECT * FROM t_punkte INNER JOIN t_user ON t_punkte.id_name= t_user._id WHERE t_punkte.id_name=" + user_id + " ORDER BY punkte DESC", null);

        anzeigeSpalten = new String[] { SQLiteManager.ID ,  SQLiteManager.USERNAME, SQLiteManager.PUNKTE, SQLiteManager.DATUM};

        int[] anzeigeViews = new int[] { R.id.tv_id_user , R.id.tv_username_highscore, R.id.tv_punkte_highscore, R.id.tv_datum};

        MyCursorAdapter adapter = new MyCursorAdapter(this, R.layout.listen_layout ,cursor , anzeigeSpalten, anzeigeViews, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        int asd = adapter.getCount();
        String asd1 = String.valueOf(asd);

        for(int i=0; i < adapter.getCount(); i++) {


            if((i % 2) == 0) {

            } else {

            }
        }

        lv_highscore.setAdapter(adapter) ;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }


}




