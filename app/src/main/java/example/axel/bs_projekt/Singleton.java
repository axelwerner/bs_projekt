<<<<<<< Updated upstream
package example.axel.bs_projekt;

/**
 * Created by Axel on 14.11.2014.
 */
public class Singleton {

    int id_user;
    String name_user;
    String password;
    String email;
    byte[] image_profile;
    int score;

    private static Singleton singleton = null;
    private Singleton () {}


    public static Singleton GetInstance() {
        if(singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getImage_profile() {
        return image_profile;
    }

    public void setImage_profile(byte[] image_profile) {
        this.image_profile = image_profile;
    }


}
=======
package example.axel.bs_projekt;

/**
 * Created by Axel on 14.11.2014.
 */
public class Singleton {

    int id_user;
    String name_user;
    String password;
    String email;
    byte[] image_profile;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getImage_profile() {
        return image_profile;
    }

    public void setImage_profile(byte[] image_profile) {
        this.image_profile = image_profile;
    }


}
>>>>>>> Stashed changes
