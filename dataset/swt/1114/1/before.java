class PlaceHold {
  void resizeBounds(int width, int height, boolean notify) {
    if (redrawWindow != 0) {
      OS.gdk_window_resize(redrawWindow, width, height);
    }
    if (enableWindow != 0) {
      OS.gdk_window_resize(enableWindow, width, height);
    }
    forceResize(width, height);
    if (notify) {
      resized = true;
      sendEvent(Resize);
      if (isDisposed()) {
        return;
      }
      if (layout != null) {
        markLayout(false, false);
        updateLayout(false);
      }
    }
  }
}
