package com.sdcote.kynos.settings;

import com.sdcote.kynos.api.Pod;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

public class SettingsPod extends Pod {

    @Override
    public String getId() {
        return "kynos.settings";
    }

    @Override
    public String getDisplayName() {
        return "Settings";
    }

    @Override
    public Image getIcon() {
        // Placeholder or path to resource
        return null;
    }

    @Override
    public Node createView() {
        StackPane root = new StackPane(new Label("Kynos Settings Content"));
        root.setStyle("-fx-background-color: white;");
        return root;
    }
}