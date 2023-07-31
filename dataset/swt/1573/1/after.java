class PlaceHold {
  int gtk_style_set(int widget, int previousStyle) {
    int result = super.gtk_style_set(widget, previousStyle);
    if ((style & SWT.NO_BACKGROUND) != 0) {
      int window = gtk_widget_get_window(paintHandle());
      if (window != 0) {
        OS.gdk_window_set_back_pixmap(window, 0, false);
      }
    }
    return result;
  }
}
