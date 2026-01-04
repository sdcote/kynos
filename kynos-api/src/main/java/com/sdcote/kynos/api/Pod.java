package com.sdcote.kynos.api;

import javafx.scene.Node;
import javafx.scene.image.Image;

/**
 * This is the base class for all Kynos Pods.
 *
 * <p>Subclassing this abstract class allows the developer to quickly write applications that run in the Kynos framework.</p>
 */
public abstract class Pod {


    protected KynosContext context;



    /**
     * Internal framework method to link the Pod to the Dashboard.
     */
    public final void init(KynosContext context) {
        this.context = context;
    }

    /**
     * @return Unique identifier for this Pod (e.g., "com.sdcote.kynos.settings").
     */
    public abstract String getId();

    /**
     * @return The text displayed under the icon on the dashboard.
     */
    public abstract String getDisplayName();

    /**
     * @return The image used for the dashboard tile.
     */
    public abstract Image getIcon();

    /**
     * Factory method for the Pod's primary UI.
     * @return The root Node of the Pod's interface.
     */
    public abstract Node createView();

    /**
     * Lifecycle hook called when the Pod is loaded into memory.
     */
    public void onInitialize() {}

    /**
     * Lifecycle hook called when the Pod is launched.
     */
    public void onStart() {}

    /**
     * Lifecycle hook called when the Pod is closed (Home button pressed).
     */
    public void onStop() {}

}