package example.axel.bs_projekt;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


//ASDASD
//ASDASD
//ASDASD


/**
 * Created by luke on 29.11.14.
 */
public class Brick {

    private Bitmap brick[] = new Bitmap[10];
    private int brickX[] = new int[10];
    private int brickY[] = new int[10];
    private int step;
    private int disWidth, disHeight, brHeight;
    private CreateRandom random;

    public void Brick(CreateRandom random, int disWidth, int disHeight){

        this.random = random;
        this.disWidth = disWidth;
        this.disHeight = disHeight;
    }

    public void intBrick(Resources resources){

        // get random object
        random = new CreateRandom();

        // brick Bitmap
        for (int a = 0 ; a<10; a++){
            brick[a] = BitmapFactory.decodeResource(resources, R.drawable.cloud);
        }

        // set brick y start positions
        for (int a = 0 ; a<10; a++){
            brickY[a] = random.brickWidth(disWidth, brick[a].getWidth());
        }

        // step between bricks on x acis
        this.step = disHeight / 10;

        // current brick
        int remember = disHeight;

        // set brick x start positions
        for (int a = 0 ; a<10; a++){
            brickX[a] = remember -step;
            remember = brickX[a];
        }


    }

    public void moveBricks (int moveSpeed){

        // move bricks instead of guy
        for (int a = 0 ; a < 10 ; a++){
            brickX[a] = brickX[a] + moveSpeed;

        }
    }

    public void brickYPosition (int a){
        brickY[a] = random.brickWidth(disWidth, brick[a].getWidth());
        brickX[a] = 0;
    }

    public int brickHeight (int a){

        brHeight = brick[a].getHeight();
        return brHeight;

    }


    public Bitmap getBrick(int a) {
        return brick[a];
    }

    public int getBrickX(int a) {
        return brickX[a];
    }

    public int getBrickY(int a) {
        return brickY[a];
    }

    public void setBrickX(int brickX, int a) {
        this.brickX[a] = brickX;
    }

    public void setBrickY(int brickY, int a) {
        this.brickY[a] = brickY;
    }

    public int getStep() {
        return step;
    }

}
