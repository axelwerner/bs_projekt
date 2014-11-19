package example.axel.bs_projekt;

import android.test.SingleLaunchActivityTestCase;

/**
 * Created by Axel on 14.11.2014.
 */
public class Singleton {

    int id_user;
    String name_user;

    private static Singleton singleton = null;
    private Singleton () {}


    public static Singleton GetInstance() {
        if(singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }


    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
    }
}
