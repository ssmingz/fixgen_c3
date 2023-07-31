class PlaceHold {
  void setBackgroundPixmap(Image image) {
    long window = gtk_widget_get_window(paintHandle());
    if (window != 0) {
      if (image.pixmap != 0) {
        OS.gdk_window_set_back_pixmap(window, image.pixmap, false);
      } else if (image.surface != 0) {
        if (OS.GTK_VERSION >= OS.VERSION(3, 0, 0)) {
          long pattern = Cairo.cairo_pattern_create_for_surface(image.surface);
          if (pattern == 0) {
            SWT.error(ERROR_NO_HANDLES);
          }
          Cairo.cairo_pattern_set_extend(pattern, CAIRO_EXTEND_REPEAT);
          OS.gdk_window_set_background_pattern(window, pattern);
          Cairo.cairo_pattern_destroy(pattern);
        }
      }
    }
  }
}
