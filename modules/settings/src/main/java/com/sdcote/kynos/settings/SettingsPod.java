package com.sdcote.kynos.settings;

import com.sdcote.kynos.api.Logger;
import com.sdcote.kynos.api.Pod;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

public class SettingsPod extends Pod {

    private final Logger LOG = context.getLogger(SettingsPod.class);

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
        LOG.trace("Creating view");
        StackPane root = new StackPane(new Label("Kynos Settings Content"));
        root.setStyle("-fx-background-color: white;");

        CheckBox logToggle = new CheckBox("Enable System Logging");
        logToggle.setSelected(context.isLoggingEnabled());
        logToggle.selectedProperty().addListener((obs, oldVal, newVal) -> {
            context.setLoggingEnabled(newVal);
        });

        return root;
    }
}