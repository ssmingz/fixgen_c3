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
      case OS.EN_ALIGN_LTR_EC:
      case OS.EN_ALIGN_RTL_EC:
        Event event = new Event();
        event.doit = true;
        sendEvent(OrientationChange, event);
        if (!event.doit) {
          int hwnd = lParam;
          int bits1 = OS.GetWindowLong(hwnd, GWL_EXSTYLE);
          int bits2 = OS.GetWindowLong(hwnd, GWL_STYLE);
          if (code == OS.EN_ALIGN_LTR_EC) {
            bits1 |= OS.WS_EX_RTLREADING | OS.WS_EX_RIGHT;
            bits2 |= OS.ES_RIGHT;
          } else {
            bits1 &= ~(OS.WS_EX_RTLREADING | OS.WS_EX_RIGHT);
            bits2 &= ~OS.ES_RIGHT;
          }
          OS.SetWindowLong(hwnd, GWL_EXSTYLE, bits1);
          OS.SetWindowLong(hwnd, GWL_STYLE, bits2);
        }
        break;
    }
    return super.wmCommandChild(wParam, lParam);
  }
}
