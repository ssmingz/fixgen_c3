class PlaceHold {
  void setBackgroundPixmap(Image image) {
    int window = OS.GTK_WIDGET_WINDOW(paintHandle());
    if (window != 0) {
      if (image.pixmap != 0) {
        OS.gdk_window_set_back_pixmap(window, image.pixmap, false);
      } else if (image.surface != 0) {
      }
    }
  }
}
