module kynos.settings {
    requires kynos.api;
    requires javafx.controls;

    exports com.sdcote.kynos.settings;

    // This allows the Core's ServiceLoader to find the Pod implementation
    provides com.sdcote.kynos.api.Pod with com.sdcote.kynos.settings.SettingsPod;

    // Open to JavaFX for reflection-based UI loading
    opens com.sdcote.kynos.settings to javafx.graphics, kynos.api;
}