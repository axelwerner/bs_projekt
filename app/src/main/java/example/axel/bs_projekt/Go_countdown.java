package example.axel.bs_projekt;

import android.app.Activity;
import android.app.FragmentManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;


public class Go_countdown extends Fragment {


    FragmentManager fragmentManager;
    TextView tv_countdown;
    int countdown = 3;

    Button button_minus, button_plus;
    TextView textView_punkte_start, textView_erreichen, textview_finish, textview_punkteanzahl;
    ProgressBar progressBar;

    Random rnd;
    int punkte = 0;
    int start_nummer;
    int nummer_erreichen;
    int bar_i = 30;
    int letzte_punkte = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_go_countdown, container, false);

        tv_countdown = (TextView) view.findViewById(R.id.tv_countdown_fragment);
        tv_countdown.setText(Integer.toString(countdown));
        fragmentManager = getFragmentManager();


        new Thread(new Runnable() {
            @Override
            public void run() {

                long tempTime = System.currentTimeMillis();
                Log.d("timer", "Time diff: " + tempTime);

                for (int i = 3; i > -1; i--) {

                    Log.v("Log_tag", "Tick of Progress" + i);


                    Animation a = AnimationUtils.loadAnimation(getActivity(), R.anim.scaleanimation_text);
                    a.reset();


                    tv_countdown.setText(Integer.toString(i));
                    tv_countdown.clearAnimation();
                    tv_countdown.startAnimation(a);




                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }

                Log.d("timer", "Time diff: " + (System.currentTimeMillis() - tempTime));

                tv_countdown.setText("");
            }
        }).start();
        return view;
    }


}
