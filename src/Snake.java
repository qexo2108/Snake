import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Snake
{
    private int x;
    private int y;
    private Constants.Direction dir;

    Rectangle rect;

    public Snake(int x, int y)
    {
        this.x = x;
        this.y = y;

        rect = new Rectangle(Constants.snakeSegmentSize, Constants.snakeSegmentSize);
        rect.setFill(Constants.snakeColor);
        rect.relocate(x,y);

        dir = Constants.Direction.right;
    }


    void update()
    {
        // Przesunięcie węża
        if(dir == Constants.Direction.down)
            y += Constants.snakeSegmentSize;
        else if(dir == Constants.Direction.up)
            y-= Constants.snakeSegmentSize;
        else if(dir == Constants.Direction.left)
            x -= Constants.snakeSegmentSize;
        else
            x += Constants.snakeSegmentSize;

        // Obsługa wyjścia poza ekran
        if(x >= Constants.windowWidth)
            x = 0;
        else if(x < 0)
            x = Constants.windowWidth - Constants.snakeSegmentSize;
        else if(y >= Constants.windowHeight)
            y = 0;
        else if(y < 0)
            y = Constants.windowHeight - Constants.snakeSegmentSize;
        rect.relocate(x,y);
    }

    void setDir(Constants.Direction dir)
    {
        this.dir = dir;
    }
    Constants.Direction getDir()
    {
        return dir;
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
