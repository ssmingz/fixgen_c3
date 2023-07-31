class PlaceHold {
  void releaseWidget() {
    super.releaseWidget();
    destroyAccelGroup();
    display.clearModal(this);
    if (display.activeShell == this) {
      display.activeShell = null;
    }
    if (tooltipsHandle != 0) {
      OS.g_object_unref(tooltipsHandle);
    }
    tooltipsHandle = 0;
    if (group != 0) {
      OS.g_object_unref(group);
    }
    group = modalGroup = 0;
    int window = gtk_widget_get_window(shellHandle);
    OS.gdk_window_remove_filter(window, filterProc, shellHandle);
    lastActive = null;
  }
}
