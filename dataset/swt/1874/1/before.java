class PlaceHold {
  void remove(int index, boolean notify) {
    TCHAR buffer = null;
    if ((style & SWT.H_SCROLL) != 0) {
      int length = ((int) (OS.SendMessage(handle, CB_GETLBTEXTLEN, index, 0)));
      if (length == OS.CB_ERR) {
        int count = ((int) (OS.SendMessage(handle, CB_GETCOUNT, 0, 0)));
        if ((0 <= index) && (index < count)) {
          error(ERROR_ITEM_NOT_REMOVED);
        }
        error(ERROR_INVALID_RANGE);
      }
      buffer = new TCHAR(getCodePage(), length + 1);
      int result = ((int) (OS.SendMessage(handle, CB_GETLBTEXT, index, buffer)));
      if (result == OS.CB_ERR) {
        int count = ((int) (OS.SendMessage(handle, CB_GETCOUNT, 0, 0)));
        if ((0 <= index) && (index < count)) {
          error(ERROR_ITEM_NOT_REMOVED);
        }
        error(ERROR_INVALID_RANGE);
      }
    }
    int length = OS.GetWindowTextLength(handle);
    int code = ((int) (OS.SendMessage(handle, CB_DELETESTRING, index, 0)));
    if (code == OS.CB_ERR) {
      int count = ((int) (OS.SendMessage(handle, CB_GETCOUNT, 0, 0)));
      if ((0 <= index) && (index < count)) {
        error(ERROR_ITEM_NOT_REMOVED);
      }
      error(ERROR_INVALID_RANGE);
    }
    if ((style & SWT.H_SCROLL) != 0) {
      setScrollWidth(buffer, true);
    }
    if (notify && (length != OS.GetWindowTextLength(handle))) {
      sendEvent(Modify);
      if (isDisposed()) {
        return;
      }
    }
    if ((style & SWT.READ_ONLY) != 0) {
      int count = ((int) (OS.SendMessage(handle, CB_GETCOUNT, 0, 0)));
      if (count == 0) {
        OS.InvalidateRect(handle, null, true);
      }
    }
  }
}
