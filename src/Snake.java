import javafx.scene.layout.Pane;
import java.util.ArrayList;
import java.util.List;

class Snake
{
    private Pane layout;
    private List<SnakeSegment> snakes;

    Snake()
    {
        snakes = new ArrayList<>();
        createStartingSegments();

        layout = new Pane();
        for (SnakeSegment snake : snakes)
            layout.getChildren().add(snake.rect);
    }

    void update()
    {
        for(SnakeSegment snake : snakes)
            snake.update();
        for(int i=snakes.size()-1; i>=1; i--)
            snakes.get(i).setDir( snakes.get(i-1).getDir() );
    }

    void scored()
    {
        int last = snakes.size()-1;
        int x = snakes.get(last).getX();
        int y = snakes.get(last).getY();

        if(snakes.get(last).getDir() == Constants.Direction.right)
            x -= Constants.snakeSegmentSize;
        else if(snakes.get(last).getDir() == Constants.Direction.left)
            x += Constants.snakeSegmentSize;
        else if(snakes.get(last).getDir() == Constants.Direction.up)
            y += Constants.snakeSegmentSize;
        else if(snakes.get(last).getDir() == Constants.Direction.down)
            y -= Constants.snakeSegmentSize;

        snakes.add(new SnakeSegment(x,y, snakes.get(last).getDir()));
        layout.getChildren().add(snakes.get(last+1).rect);
    }

    void reset()
    {
        layout = new Pane();
        snakes.clear();

        createStartingSegments();
        for(SnakeSegment snake: snakes)
            layout.getChildren().add(snake.rect);
    }

    private void createStartingSegments()
    {
        snakes.add(new SnakeSegment(Constants.snakeSegmentSize*2,0, Constants.Direction.right));
        snakes.add(new SnakeSegment(Constants.snakeSegmentSize,0, Constants.Direction.right));
        snakes.add(new SnakeSegment(0,0, Constants.Direction.right));
    }


    boolean ifLost()
    {
        for(int i=1; i<snakes.size(); i++)
            if(this.getX() == snakes.get(i).getX() && this.getY() == snakes.get(i).getY())
                return true;
        return false;
    }

    int getX()
    {
        return snakes.get(0).getX();
    }
    int getY()
    {
        return snakes.get(0).getY();
    }

    void setDir(Constants.Direction dir)
    {
        snakes.get(0).setDir(dir);
    }
    Constants.Direction getDir()
    {
        return snakes.get(0).getDir();
    }

    Pane getLayout()
    {
        return layout;
    }
}
