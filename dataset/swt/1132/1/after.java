class PlaceHold {
  void releaseWidget() {
    releaseShells();
    destroyAccelGroup();
    super.releaseWidget();
    if (display.activeShell == this) {
      display.activeShell = null;
    }
    if (tooltipsHandle != 0) {
      OS.g_object_unref(tooltipsHandle);
    }
    tooltipsHandle = 0;
    if (OS.GDK_WINDOWING_X11()) {
      int window = OS.GTK_WIDGET_WINDOW(shellHandle);
      OS.gdk_window_remove_filter(window, filterProc, shellHandle);
    }
    region = null;
    lastActive = null;
  }
}
