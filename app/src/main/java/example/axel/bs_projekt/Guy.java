package example.axel.bs_projekt;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by luke on 29.11.14.
 */
public class Guy {

    private Bitmap Guy;
    private int guyX;
    private int guyY;
    private int height;
    private int width;


    public void Guy(){}

    public void intGuy(int brickY, int brickX, Resources resources){

        // Bitmap
        this.Guy = BitmapFactory.decodeResource(resources, R.drawable.ic_launcher);

        // set Guy start position (start on first brick!)
        this.guyX = brickX - Guy.getHeight();
        this.guyY = brickY+(Guy.getWidth()/4);

        // get Guy height and width
        this.height = Guy.getHeight();
        this.width = Guy.getWidth();


    }

    public void setGuyY(int guyY) {
        this.guyY = guyY;
    }

    public void setGuyX(int guyX) {
        this.guyX = guyX;
    }

    public Bitmap getGuy() {
        return Guy;
    }

    public int getGuyX() {
        return guyX;
    }

    public int getGuyY() {
        return guyY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


}
