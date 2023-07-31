class PlaceHold {
  void update(boolean all) {
    if (isPainting.containsObject(view)) {
      return;
    }
    Shell shell = getShell();
    NSWindow window = (shell.deferFlushing && shell.scrolling) ? view.window() : null;
    try {
      if (window != null) {
        window.retain();
        window.disableFlushWindow();
      }
      view.displayIfNeeded();
    } finally {
      if (window != null) {
        window.enableFlushWindow();
        window.release();
      }
    }
  }
}
