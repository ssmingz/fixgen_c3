class PlaceHold {
  Color getForegroundColor() {
    return foreground != null ? Color.cocoa_new(display, foreground) : defaultForeground();
  }
}
