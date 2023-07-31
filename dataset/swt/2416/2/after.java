class PlaceHold {
  LRESULT wmCommandChild(int wParam, int lParam) {
    int code = OS.HIWORD(wParam);
    switch (code) {
      case OS.EN_CHANGE:
        if (ignoreModify) {
          break;
        }
        int value = getSelectionText();
        if (value != (-1)) {
          int pos;
          if (OS.IsWinCE) {
            pos = OS.LOWORD(OS.SendMessage(hwndUpDown, UDM_GETPOS, 0, 0));
          } else {
            pos = ((int) (OS.SendMessage(hwndUpDown, UDM_GETPOS32, 0, 0)));
          }
          if (pos != value) {
            setSelection(value, true, false, true);
          }
        }
        sendEvent(Modify);
        if (isDisposed()) {
          return LRESULT.ZERO;
        }
        break;
    }
    return super.wmCommandChild(wParam, lParam);
  }
}
