package example.axel.bs_projekt;

import android.widget.TextView;

/**
 * Created by luke on 29.11.14.
 */
public class GameEngine {

    private boolean jumpFlag = true;
    private int jumpCurrent;
    private static int maxJump = 200;
    private static int moveSpeed = 4;
    private static int directionSpeed = 6;
    private int guyX, guyY;
    private CreateRandom createRandom;
    private Guy guy;
    private Brick brick;
    private TextView punkte_anzahl;
    private Singleton singleton;



    public void GameEngine(CreateRandom random, Guy guy, Brick brick){

        this.createRandom = random;
        this.guy = guy;
        this.brick = brick;

    }
    public void directionMove(int touchY){

        this.guyY = guy.getGuyY();

        if (touchY < guy.getGuyY()){
           this.guyY = guyY - directionSpeed;
           guy.setGuyY(guyY);
        }

        if (touchY > guy.getGuyY()){
            this.guyY = guyY + directionSpeed;
            guy.setGuyY(guyY);
        }
    }

    public void jump(){

        this.guyX = guy.getGuyX();
        this.guyY = guy.getGuyY();

        if (jumpFlag){

            if (guy.getGuyX()>300) {

                // move guy to top(jump)
                guyX = guyX - moveSpeed;


            }
            // remember the current jumped height
            this.jumpCurrent = jumpCurrent + moveSpeed;

            if (jumpCurrent >= maxJump){

                this.jumpFlag = false;
                this.jumpCurrent = 0;



            }


        }
        else{


                guyX = guyX + moveSpeed;
                checkSolidBrick();

        }

        if (guy.getGuyX()<=300){

            brick.moveBricks(moveSpeed);

        }

        guy.setGuyX(guyX);




    }

    public void checkSolidBrick(){

        if (jumpFlag == false) {
            for (int a = 0; a < 10; a++) {

                // check x acis
                if (guyX + guy.getHeight() >= (brick.getBrickX(a)+ brick.brickHeight(a)/2) - moveSpeed && guyX + guy.getHeight() <= (brick.getBrickX(a)+ brick.brickHeight(a)/2) + moveSpeed) {

                    // check y acis
                    if (guyY + guy.getWidth() >= (brick.getBrickY(a)+20) && guyY <= (brick.getBrickY(a) + brick.getBrick(a).getWidth()-20)) {

                        // set jump true again
                        jumpFlag = true;
                    }
                }

            }
        }
    }

    public void reorganizeBrick(int displayWidth){

        for (int a = 0 ; a < 10 ; a ++){

            if (brick.getBrickX(a) >= displayWidth ){
                brick.brickYPosition(a);
            }
        }
    }

    public void score() {



    }

}
