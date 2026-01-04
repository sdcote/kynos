package com.sdcote.kynos.core;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.util.Map;

public class KynosLauncher extends Application {

    @Override
    public void start(Stage primaryStage) {
        // 1. Process Command-Line Parameters
        Parameters params = getParameters();
        Map<String, String> namedParams = params.getNamed();

        String mode = namedParams.getOrDefault("mode", "window");
        int width = Integer.parseInt(namedParams.getOrDefault("width", "1024"));
        int height = Integer.parseInt(namedParams.getOrDefault("height", "768"));

        // 2. Initialize Root Layout and Controller
        BorderPane root = new BorderPane();
        KynosController controller = new KynosController(root);

        // 3. Configure the Stage
        if ("kiosk".equalsIgnoreCase(mode)) {
            primaryStage.initStyle(StageStyle.UNDECORATED);
            primaryStage.setFullScreen(true);
            primaryStage.setFullScreenExitHint("");
        } else {
            primaryStage.setTitle("Kynos Launcher");
            primaryStage.setWidth(width);
            primaryStage.setHeight(height);
        }

        // 4. Discover and Load Pods
        // This assumes a "pods" directory exists in the current working directory
        File podsDir = new File("pods");
        if (!podsDir.exists()) {
            podsDir.mkdir();
        }
        controller.discoverPods(podsDir);

        // 5. Set the Scene and Show
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

        // 6. Navigate to the initial dashboard view
        controller.goHome();
    }

    public static void main(String[] args) {
        launch(args);
    }
}