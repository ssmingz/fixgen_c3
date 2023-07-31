class PlaceHold {
  boolean hasFocus() {
    return OS.GTK_WIDGET_HAS_FOCUS(handle);
  }
}
