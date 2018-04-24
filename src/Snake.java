import javafx.scene.shape.Rectangle;

public class Snake
{
    private int x;
    private int y;
    private Constants.Direction dir;
    private Constants.Direction inputDir;

    Rectangle rect;

    public Snake(int x, int y, Constants.Direction dir)
    {
        this.x = x;
        this.y = y;
        this.dir = dir;
        inputDir = dir;

        rect = new Rectangle(Constants.snakeSegmentSize, Constants.snakeSegmentSize);
        rect.setFill(Constants.snakeColor);
        rect.relocate(x,y);
    }


    void update()
    {
        // Load input
        dir = inputDir;

        // Move the snake
        if(dir == Constants.Direction.down)
            y += Constants.snakeSegmentSize;
        else if(dir == Constants.Direction.up)
            y-= Constants.snakeSegmentSize;
        else if(dir == Constants.Direction.left)
            x -= Constants.snakeSegmentSize;
        else
            x += Constants.snakeSegmentSize;

        // Handling out of window situation
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
        this.inputDir = dir;
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
