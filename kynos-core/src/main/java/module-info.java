module kynos.core {
    requires javafx.controls;
    requires javafx.graphics;
    requires kynos.api;

    // Core will scan for any implementation of Pod
    uses com.sdcote.kynos.api.Pod;

    opens com.sdcote.kynos.core to javafx.graphics;
}