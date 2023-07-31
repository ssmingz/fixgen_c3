class PlaceHold {
  public void setCursor(Cursor cursor) {
    if (!isValidThread()) {
      error(ERROR_THREAD_INVALID_ACCESS);
    }
    if (!isValidWidget()) {
      error(ERROR_WIDGET_DISPOSED);
    }
    int display = OS.XtDisplay(handle);
    if (display == 0) {
      return;
    }
    int window = OS.XtWindow(handle);
    if (window == 0) {
      if (!OS.XtIsRealized(handle)) {
        getShell().realizeWidget();
      }
      window = OS.XtWindow(handle);
      if (window == 0) {
        return;
      }
    }
    if (cursor == null) {
      OS.XUndefineCursor(display, window);
    } else {
      int xCursor = cursor.handle;
      OS.XDefineCursor(display, window, xCursor);
      OS.XFlush(display);
    }
  }
}
