class PlaceHold {
  void HandleSizeChanged(int sender, int e) {
    if (!checkEvent(e)) {
      return;
    }
    int width = ((int) (OS.FrameworkElement_ActualWidth(shellHandle)));
    int height = ((int) (OS.FrameworkElement_ActualHeight(shellHandle)));
    if (((!resized) || (oldWidth != width)) || (oldHeight != height)) {
      resized = true;
      oldWidth = width;
      oldHeight = height;
      sendEvent(Resize);
      if (isDisposed()) {
        return;
      }
      if (layout != null) {
        markLayout(false, false);
        updateLayout(false, false);
      }
    }
  }
}
