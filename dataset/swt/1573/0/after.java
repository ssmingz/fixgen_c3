class PlaceHold {
  int gtk_realize(int widget) {
    int result = super.gtk_realize(widget);
    if ((style & SWT.NO_BACKGROUND) != 0) {
      int window = gtk_widget_get_window(paintHandle());
      if (window != 0) {
        OS.gdk_window_set_back_pixmap(window, 0, false);
      }
    }
    if (socketHandle != 0) {
      embeddedHandle = OS.gtk_socket_get_id(socketHandle);
    }
    return result;
  }
}
