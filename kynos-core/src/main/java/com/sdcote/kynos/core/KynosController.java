package com.sdcote.kynos.core;

import com.sdcote.kynos.api.KynosContext;
import com.sdcote.kynos.api.Pod;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ServiceLoader;
import java.util.ArrayList;
import java.util.List;

public class KynosController implements KynosContext {
    private final BorderPane root;
    private final TilePane dashboardGrid;
    private final Label timeLabel;
    private final List<Pod> loadedPods = new ArrayList<>();

    public KynosController(BorderPane root) {
        this.root = root;
        this.timeLabel = new Label("12:00 PM");
        this.dashboardGrid = createDashboardGrid();
        this.root.setTop(createStatusBar());
        this.root.setCenter(dashboardGrid);
    }

    private HBox createStatusBar() {
        HBox bar = new HBox(15);
        bar.setPadding(new Insets(10));
        bar.setStyle("-fx-background-color: #2c3e50;");
        bar.setAlignment(Pos.CENTER_LEFT);

        Button homeBtn = new Button("⌂");
        homeBtn.setOnAction(e -> goHome());

        timeLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");

        bar.getChildren().addAll(homeBtn, timeLabel);
        return bar;
    }

    private TilePane createDashboardGrid() {
        TilePane grid = new TilePane();
        grid.setPadding(new Insets(25));
        grid.setHgap(25);
        grid.setVgap(25);
        grid.setStyle("-fx-background-color: #ecf0f1;");
        return grid;
    }

    /**
     * Scans a directory for JAR files and loads Pod implementations.
     * @param podsDirectory The folder to monitor for plug-ins.
     */
    public void discoverPods(File podsDirectory) {
        if (!podsDirectory.exists() || !podsDirectory.isDirectory()) {
            return;
        }

        File[] files = podsDirectory.listFiles((dir, name) -> name.endsWith(".jar"));
        if (files == null) return;

        try {
            URL[] urls = new URL[files.length];
            for (int i = 0; i < files.length; i++) {
                urls[i] = files[i].toURI().toURL();
            }

            // Create a classloader that includes the found JARs
            URLClassLoader childLoader = new URLClassLoader(urls, this.getClass().getClassLoader());

            // Use ServiceLoader to find implementations of Pod within those JARs
            ServiceLoader<Pod> loader = ServiceLoader.load(Pod.class, childLoader);

            for (Pod pod : loader) {
                pod.init(this);
                pod.onInitialize();
                loadedPods.add(pod);
                addPodToDashboard(pod);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addPodToDashboard(Pod pod) {
        VBox tile = new VBox(10);
        tile.setAlignment(Pos.CENTER);
        tile.setPrefSize(140, 140);
        tile.setStyle("-fx-background-color: white; -fx-background-radius: 15; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);");

        Label nameLabel = new Label(pod.getDisplayName());

        // Use a placeholder if getIcon() returns null initially
        Node icon = (pod.getIcon() != null) ? new ImageView(pod.getIcon()) : new Label("📦");

        tile.getChildren().addAll(icon, nameLabel);
        tile.setOnMouseClicked(e -> launchPod(pod));

        dashboardGrid.getChildren().add(tile);
    }

    private void launchPod(Pod pod) {
        pod.onStart();
        root.setCenter(pod.createView());
    }

    @Override
    public void goHome() {
        root.setCenter(dashboardGrid);
    }

    @Override
    public void updateStatusBarMessage(String message) {
        Platform.runLater(() -> System.out.println("Kynos Status: " + message));
    }
}