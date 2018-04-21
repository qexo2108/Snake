
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;


public class Main extends Application
{
    private Pane layout;
    private Scene scene;

    private List<Snake> snakes = new LinkedList<>();
    private Food food;

    private int score = 0;

    private AnimationTimer timer;


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        snakes.add(new Snake(Constants.snakeSegmentSize*2,0));
        snakes.add(new Snake(Constants.snakeSegmentSize,0));
        snakes.add(new Snake(0,0));

        food = new Food();

        layout = new Pane();
        for (Snake snake : snakes)
            layout.getChildren().add(snake.rect);
        layout.getChildren().add(food.rect);

        scene = new Scene(layout, Constants.windowWidth, Constants.windowHeight, Color.BLACK);

        primaryStage.setTitle("This is snaaaaakeeeeee");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Update (called in every frame)
        timer = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                for (Snake snake : snakes)
                    snake.update();
                if(snakes.get(0).getX() == food.getX() && snakes.get(0).getY() == food.getY())
                    scored();
                else
                    try
                    { Thread.sleep(100); }
                    catch(InterruptedException e) {}

                for(int i=1; i<snakes.size(); i++)
                    if(snakes.get(0).getX() == snakes.get(i).getX() && snakes.get(0).getY() == snakes.get(i).getY())
                        lost();

                for(int i=snakes.size()-1; i>=1; i--)
                    snakes.get(i).setDir( snakes.get(i-1).getDir() );
            }

        };

        scene.setOnKeyPressed(new EventHandler<KeyEvent>()
        {
            @Override
            public void handle(KeyEvent event)
            {
                switch (event.getCode())
                {
                    case UP:
                        if(snakes.get(0).getDir() != Constants.Direction.down)
                            snakes.get(0).setDir(Constants.Direction.up);
                    break;
                    case DOWN:
                        if(snakes.get(0).getDir() != Constants.Direction.up)
                            snakes.get(0).setDir(Constants.Direction.down);
                    break;
                    case LEFT:
                        if(snakes.get(0).getDir() != Constants.Direction.right)
                            snakes.get(0).setDir(Constants.Direction.left);
                    break;
                    case RIGHT:
                        if(snakes.get(0).getDir() != Constants.Direction.left)
                            snakes.get(0).setDir(Constants.Direction.right);
                    break;
                    case ESCAPE:
                        reset(primaryStage);
                    break;
                }
            }
        });

        timer.start();
    }

    void scored()
    {
        score++;
        System.out.println("You scored! Score: " + score);
        food.relocate();
        int x = snakes.get(snakes.size()-1).getX();
        int y = snakes.get(snakes.size()-1).getY();

        if(snakes.get(snakes.size()-2).getDir() == Constants.Direction.right)
            x -= Constants.snakeSegmentSize;
        else if(snakes.get(snakes.size()-2).getDir() == Constants.Direction.left)
            x += Constants.snakeSegmentSize;
        else if(snakes.get(snakes.size()-2).getDir() == Constants.Direction.up)
            y += Constants.snakeSegmentSize;
        else if(snakes.get(snakes.size()-2).getDir() == Constants.Direction.down)
            y -= Constants.snakeSegmentSize;


        snakes.add(new Snake(x,y));
        layout.getChildren().add(snakes.get(snakes.size()-1).rect);
    }

    void lost()
    {
        timer.stop();
        System.out.println("You lost. Scored " + score + " points.");
    }

    void reset(Stage primaryStage)
    {
        timer.stop();
        layout.getChildren().removeAll();
        snakes.clear();

        snakes.add(new Snake(Constants.snakeSegmentSize*2,0));
        snakes.add(new Snake(Constants.snakeSegmentSize,0));
        snakes.add(new Snake(0,0));

        food = new Food();

        layout.getChildren().add(new Rectangle(Constants.windowWidth, Constants.windowHeight, Color.BLACK));
        for(Snake snake: snakes)
            layout.getChildren().add(snake.rect);
        layout.getChildren().add(food.rect);

        System.out.println("New game");
        timer.start();
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
