class PlaceHold {
  LRESULT wmChar(int hwnd, int wParam, int lParam) {
    if (ignoreCharacter) {
      return null;
    }
    LRESULT result = super.wmChar(hwnd, wParam, lParam);
    if (result != null) {
      return result;
    }
    switch (wParam) {
      case SWT.TAB:
        return LRESULT.ZERO;
      case SWT.CR:
        postEvent(DefaultSelection);
      case SWT.ESC:
        if (OS.SendMessage(handle, CB_GETDROPPEDSTATE, 0, 0) == 0) {
          return LRESULT.ZERO;
        }
    }
    return result;
  }
}
