
package views;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Simple "Hello World" Application - Lambda Expression
 * btn.setOnAction specifies what happens when you select the button represented by the btn object. This method requires an object of type EventHandler<ActionEvent>.
 * This interface is a functional interface, so you could use the following highlighted lambda expression to replace it:
 *   btn.setOnAction(
          event -> System.out.println("Hello World!")
          * NetBeans actually offers you this choice to "use a lambda expressions" in the side tool bar - 
          
 * @author gilemerson
 */
public class LambdaExpressionsEx1 extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
 
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setTitle("Hello World Application!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
 public static void main(String[] args) {
        launch(args);
        
    }
}