class PlaceHold {
  public void setCursor(Cursor cursor) {
    checkWidget();
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
      if (cursor.isDisposed()) {
        SWT.error(ERROR_INVALID_ARGUMENT);
      }
      int xCursor = cursor.handle;
      OS.XDefineCursor(display, window, xCursor);
      OS.XFlush(display);
    }
  }
}
