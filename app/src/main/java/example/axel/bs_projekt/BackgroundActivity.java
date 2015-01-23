package example.axel.bs_projekt;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

<<<<<<< Updated upstream
// TEST TEST TEST TEST TEST
// TEST TEST TEST TEST TEST
// TEST TEST TEST TEST TEST
// TEST TEST TEST TEST TEST
// TEST TEST TEST TEST TEST


=======
>>>>>>> Stashed changes

public class BackgroundActivity extends Activity {

    FragmentManager fm;
    FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_background);

        fm = getFragmentManager();
        ft = fm.beginTransaction();

        FragmentLogin fragLogin = new FragmentLogin();
        ft.add(R.id.backgroundLayout, fragLogin);
        ft.commit();

    }



}
