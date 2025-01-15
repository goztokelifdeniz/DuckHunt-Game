import javafx.animation.TranslateTransition;
import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;

public class Duck {
    Bounds bounds;

    ImageView imageView;
    TranslateTransition falling_translateTransition;

    TranslateTransition flying_translateTransition;


    Duck(){
        this.flying_translateTransition = new TranslateTransition();
        this.falling_translateTransition = new TranslateTransition();
    }
}
