package sample;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static sample.SlotMachine.generateRandomInt;

public class Reel {

    private static Image img_seven = new Image("/Images/redseven.png");
    private static Image img_bell = new Image("/Images/bell.png");
    private static Image img_watermelon = new Image("/Images/watermelon.png");
    private static Image img_plum = new Image("/Images/plum.png");
    private static Image img_lemon = new Image("/Images/lemon.png");
    private static Image img_cherry = new Image("/Images/cherry.png");

    private static Symbol seven = new Symbol(7, img_seven);
    private static Symbol bell = new Symbol(6, img_bell);
    private static Symbol watermelon = new Symbol(5, img_watermelon);
    private static Symbol plum = new Symbol(4, img_plum);
    private static Symbol lemon = new Symbol(3, img_lemon);
    private static Symbol cherry = new Symbol(2, img_cherry);

    static Image img_empty = new Image("/Images/question_mark.png");
    //static Symbol empty = new Symbol(0, img_empty);

    static List<Symbol> symbolList = Arrays.asList(seven, bell, watermelon, plum, lemon, cherry);

    public static List<Symbol> spin() {

        Collections.shuffle(symbolList);
        return symbolList;
    }
}

class myReel extends Thread {
    ImageView imageView;
    private Symbol reelObj;

    public myReel(ImageView imageView) {
        this.imageView = imageView;
    }

    public Symbol getReelObj(){
        return this.reelObj;
    }


    @Override
    public void run() {
        m1();
    }
    public void m1() {
        Symbol temp;

        while (true) {

            temp = Reel.symbolList.get(generateRandomInt(6));
            System.out.println(Thread.currentThread().getId() + "|||||" + temp.getValue());
            imageView.setImage(temp.getImage());
            reelObj = temp;
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


            /*if(isClicked== true) {
                System.out.println("Temp = "+ temp.getValue());
                slots.add(temp);
                break;
            }*/
        }
    }
}
/*    public void function(Symbol slots, ImageView imageView) {
        slots = spin().get(generateRandomInt(symbolList.size()));
        System.out.println(slots.getValue());
        imageView.setImage(slots.getImage());
    }*/

    /*@Override
    public void run() {


        slots = spin().get(generateRandomInt(symbolList.size()));
        System.out.println(Thread.currentThread().getId() + "||||||" +slots.getValue());
        System.out.println("Symbol "+slots.hashCode()+"\nThread "+ Thread.currentThread().getName());

        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                image.setImage(slots.getImage());

                //System.out.println(slots.getValue());
            }
        });
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Slot 1 - "+Dashboard.slot1.getValue());
            System.out.println("Slot 2 - "+Dashboard.slot2.getValue());
            System.out.println("Slot 3 - "+Dashboard.slot3.getValue());
            //System.out.println("SLOT 1 - " + Dashboard.slot1.hashCode());
            //System.out.println("SLOT 2 - " + Dashboard.slot2.hashCode());
            //System.out.println("SLOT 3 - " + Dashboard.slot3.hashCode());
    }*/




        /*for (int i=0; i<20; i++) {
            slots = spin().get(SlotMachine.generateRandomInt(spin().size()));
            //image.setImage(slots.getImage());
            System.out.println(slots.getValue());
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
      }
    }*/



