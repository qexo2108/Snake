
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;



public class Game extends Application
{
    private Pane layout;

    private Snake snake;
    private Food food;

    private int score = 0;

    private AnimationTimer timer;


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        snake = new Snake();

        food = new Food();

        layout = new Pane();

        layout.getChildren().addAll(snake.getLayout(), food.rect);

        Scene scene = new Scene(layout, Constants.windowWidth, Constants.windowHeight, Color.BLACK);

        primaryStage.setTitle("This is snaaaaakeeeeee");
        primaryStage.setScene(scene);
        primaryStage.show();

        printWelcomeMessage();

        // Update (called in every frame)
        timer = new AnimationTimer()
        {
            @Override
            public void handle(long now)
            {
                snake.update();
                if(snake.ifLost())
                    lost();

                if(snake.getX() == food.getX() && snake.getY() == food.getY())
                    scored();
                else
                    try
                    { Thread.sleep(100); }
                    catch(InterruptedException e)
                    {
                        System.out.println("Thread.sleep exception.");
                    }
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
                        if(snake.getDir() != Constants.Direction.down)
                            snake.setDir(Constants.Direction.up);
                        break;
                    case DOWN:
                        if(snake.getDir() != Constants.Direction.up)
                            snake.setDir(Constants.Direction.down);
                        break;
                    case LEFT:
                        if(snake.getDir() != Constants.Direction.right)
                            snake.setDir(Constants.Direction.left);
                        break;
                    case RIGHT:
                        if(snake.getDir() != Constants.Direction.left)
                            snake.setDir(Constants.Direction.right);
                        break;
                    case ESCAPE:
                        reset();
                        break;
                    case Z:
                        timer.stop();
                        break;
                    case X:
                        timer.start();
                        break;
                }
            }
        });

        timer.start();
    }

    private void scored()
    {
        score++;
        System.out.println("You scored! Score: " + score);
        food.relocate();

        snake.scored();
    }

    private void lost()
    {
        timer.stop();
        System.out.println("You lost. Scored " + score + " points.");
    }

    private void reset()
    {
        timer.stop();
        layout.getChildren().removeAll();

        snake.reset();

        food = new Food();

        layout.getChildren().add(new Rectangle(Constants.windowWidth, Constants.windowHeight, Color.BLACK));
        layout.getChildren().addAll(snake.getLayout(), food.rect);

        score = 0;
        System.out.println("New game");
        timer.start();
    }

    private void printWelcomeMessage()
    {
        System.out.println("Welcome to the classic snake game!");
        System.out.println("Control snake movement with arrows,");
        System.out.println("press ESC to start a new game,");
        System.out.println("press Z to pause game and X to continue.");
        System.out.println("Good luck :)");
    }

    public static void main(String[] args)
    {
        launch(args);
    }
}
