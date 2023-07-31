class PlaceHold {
  LRESULT wmCommandChild(int wParam, int lParam) {
    int code = OS.HIWORD(wParam);
    switch (code) {
      case OS.CBN_EDITCHANGE:
        if (ignoreModify) {
          break;
        }
        noSelection = true;
        sendEvent(Modify);
        if (isDisposed()) {
          return LRESULT.ZERO;
        }
        noSelection = false;
        break;
      case OS.CBN_SELCHANGE:
        int index = ((int) (OS.SendMessage(handle, CB_GETCURSEL, 0, 0)));
        if (index != OS.CB_ERR) {
          OS.SendMessage(handle, CB_SETCURSEL, index, 0);
        }
        sendEvent(Modify);
        if (isDisposed()) {
          return LRESULT.ZERO;
        }
        postEvent(Selection);
        break;
      case OS.CBN_SETFOCUS:
        sendFocusEvent(FocusIn);
        if (isDisposed()) {
          return LRESULT.ZERO;
        }
        break;
      case OS.CBN_KILLFOCUS:
        if ((style & SWT.READ_ONLY) != 0) {
          break;
        }
        sendFocusEvent(FocusOut);
        if (isDisposed()) {
          return LRESULT.ZERO;
        }
        break;
    }
    return super.wmCommandChild(wParam, lParam);
  }
}
