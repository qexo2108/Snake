import javafx.scene.shape.Rectangle;
import java.util.Random;

class Food
{
    private int x;
    private int y;

    Rectangle rect = new Rectangle(Constants.snakeSegmentSize, Constants.snakeSegmentSize, Constants.foodColor);

    Food()
    {
        relocate();
    }

    void relocate()
    {
        Random generator = new Random();
        x = generator.nextInt(Constants.windowWidth/Constants.snakeSegmentSize);
        x *= Constants.snakeSegmentSize;
        y = generator.nextInt(Constants.windowHeight/Constants.snakeSegmentSize);
        y *= Constants.snakeSegmentSize;
        rect.relocate(x,y);
    }

    int getX()
    {
        return x;
    }
    int getY()
    {
        return y;
    }
}
