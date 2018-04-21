import javafx.scene.paint.Color;

public class Constants
{
    public static final int windowWidth = 700;
    public static final int windowHeight = 400;

    public static final int   snakeSegmentSize = 10;
    public static final Color snakeColor = Color.BLUE;

    public static final Color foodColor = Color.GREEN;

    enum Direction
    {
        up, down, left, right
    };
}
