class PlaceHold {
  void releaseWidget() {
    super.releaseWidget();
    setVisible(false);
    if (layoutText != 0) {
      OS.g_object_unref(layoutText);
    }
    layoutText = 0;
    if (layoutMessage != 0) {
      OS.g_object_unref(layoutMessage);
    }
    layoutMessage = 0;
    if (timerId != 0) {
      OS.gtk_timeout_remove(timerId);
    }
    timerId = 0;
    text = null;
    message = null;
    borderPolygon = null;
  }
}
