package example.axel.bs_projekt;

/**
 * Created by luke on 29.11.14.
 */
public class CreateRandom {

    // random brick y position
    public int brickWidth (int dWidth, int bSize){

        int brickW;

        brickW = (int) ((Math.random())*(dWidth-bSize));

        return brickW;
    }


}
