class PlaceHold {
  public boolean print(GC gc) {
    checkWidget();
    if (gc == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (gc.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    int topHandle = topHandle();
    OS.gtk_widget_realize(topHandle);
    int window = OS.GTK_WIDGET_WINDOW(topHandle);
    GCData data = gc.getGCData();
    OS.gdk_window_process_updates(window, true);
    int drawable = data.drawable;
    if (drawable == 0) {
      drawable = OS.gdk_get_default_root_window();
    }
    printWidget(gc, drawable, OS.gdk_drawable_get_depth(drawable), 0, 0);
    return true;
  }
}
