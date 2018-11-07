package sort_insertion;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

/**
 * Visualizes the Insertion sort algorithm. For more information visit:
 * https://en.wikipedia.org/wiki/Insertion_sort
 *
 * @author Samuel Bangslund
 */
public class Sort_Insertion extends Application {

    final static int SPEED = 1;     // Speed of the sort loop. The lower the faster.

    static GraphicsContext gc;      // Global GraphicsContext variable.

    static int width = 1000;        // Width of the canvas / screen.
    static int height = 800;        // Height of the canvas / screen.
    static int count = 0;           // The count variable used in the sort loop.

    static int i;
    static boolean drawBox = false;  // Whether to draw boxes or lines.

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Insertion sort");   // Sets the title in the window.

        Group root = new Group();                 // Creates a new group. (For JavaFX components)
        Canvas canvas = new Canvas(width, height); // Creates a new canvas with width and height.
        gc = canvas.getGraphicsContext2D(); // Gets the GraphicsContext for that canvas.

        int[] array = new int[width];       // Creates an array with the size of the canvas width.
        array = fillArray(array, height);   // Fills the array with random values. 
        sort(array);                        // Sorts the array with the bubblesort algorithm.

        root.getChildren().add(canvas);         // Adds the canvas to the JavaFX group.
        primaryStage.setScene(new Scene(root)); // Sets a scene with the group.
        primaryStage.show();                    // Shows the scene.
    }

    /**
     * Fills a given array with a random value between 0 and max.
     *
     * @param array to fill.
     * @param max the max random value.
     * @return returns the filled array.
     */
    static int[] fillArray(int[] array, int max) {
        for (int i = 0; i < array.length; i++) {
            array[i] = (int) (Math.random() * max); // Inserts a random number between 0 and max.
        }

        return array;
    }

    /**
     * Displays an array as boxes or lines.
     *
     * @param array array to visualize.
     */
    static void displayArray(int[] array, boolean drawBox) {
        gc.clearRect(0, 0, width, height);
        for (int i = 0; i < array.length; i++) {
            if (drawBox) {
                gc.strokeRect(i, height - array[i], 1, 1);      // Draw box.
            } else {
                gc.strokeLine(i, height, i, height - array[i]); // Draw line.
            }
        }
    }

    /**
     * Continuously sorts the given array and visualizes it while doing so. This
     * is based on the bubble sort algorithm.
     *
     * @param A array to visualize.
     */
    void sort(int[] A) {
        i = 1;
        new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Insertion sort algorithm. Based on the psuodo code provided in the wiki.
                // https://en.wikipedia.org/wiki/Insertion_sort
                if (i < A.length) {
                    int j = i;
                    while (j > 0 && A[j - 1] > A[j]) {
                        swap(A, j, j - 1);
                        j--;

                        displayArray(A, drawBox);
                    }
                    i++;
                }
            }
        }.start();
    }

    private void swap(int[] A, int i, int j) {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}
