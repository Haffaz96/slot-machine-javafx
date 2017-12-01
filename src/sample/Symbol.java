package sample;
import javafx.scene.image.Image;

public class Symbol implements ISymbol{
    private int value = 0;
    private Image image = Reel.img_empty;
//    private Image image = new Image("/Images/question_mark.png");;

    protected Symbol(int value, Image image) {
        setImage(image);
        setValue(value);
    }

    @Override
    public void setImage(Image image ) {
        this.image = image;

    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
