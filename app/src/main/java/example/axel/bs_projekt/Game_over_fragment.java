package example.axel.bs_projekt;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class Game_over_fragment extends Fragment implements View.OnClickListener {


    private TextView tv_text;
    private Bundle bundle;
    private Button button_try_again;
    private Button button_menue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_game_over_fragment, container, false);

        TextView tv_text = (TextView) view.findViewById(R.id.tv_test);
        button_try_again = (Button) view.findViewById(R.id.b_try_again);
        button_menue = (Button) view.findViewById(R.id.b_menue);

        button_try_again.setOnClickListener(this);
        button_menue.setOnClickListener(this);



        bundle = getArguments();
        int s = bundle.getInt("test");
        String punkte_anzahl = String.valueOf(s);
        Log.e("value Fragment get Argument ", "friendsID :" + punkte_anzahl + "     " + System.currentTimeMillis());
        tv_text.setText("Sie haben " + punkte_anzahl + " Punkte erziehlt ");
        return view;

    }


    @Override
    public void onClick(View v) {

        if(v == button_try_again) {

            Intent intent = new Intent(getActivity(), MyActivity.class);
            startActivity(intent);

        }

        if(v == button_menue) {
            Intent intent = new Intent(getActivity(), HomeView.class);
            startActivity(intent);
        }

    }

}
