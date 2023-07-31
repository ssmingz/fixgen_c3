class PlaceHold {
  public void setCursor(Cursor cursor) {
    checkWidget();
    this.cursor = cursor;
    if (!isEnabled()) {
      return;
    }
    int xDisplay = OS.XtDisplay(handle);
    if (xDisplay == 0) {
      return;
    }
    int xWindow = OS.XtWindow(handle);
    if (xWindow == 0) {
      return;
    }
    if (cursor == null) {
      OS.XUndefineCursor(xDisplay, xWindow);
    } else {
      if (cursor.isDisposed()) {
        SWT.error(ERROR_INVALID_ARGUMENT);
      }
      OS.XDefineCursor(xDisplay, xWindow, cursor.handle);
      OS.XFlush(xDisplay);
    }
  }
}
