class PlaceHold {
  @Override
  long gtk_realize(long widget) {
    long result = super.gtk_realize(widget);
    if ((style & SWT.NO_BACKGROUND) != 0) {
      long window = gtk_widget_get_window(paintHandle());
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
