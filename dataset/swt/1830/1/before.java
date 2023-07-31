class PlaceHold {
  LRESULT wmChar(int hwnd, int wParam, int lParam) {
    if (ignoreCharacter) {
      return null;
    }
    LRESULT result = super.wmChar(hwnd, wParam, lParam);
    if (result != null) {
      return result;
    }
    switch (((int) (wParam))) {
      case SWT.TAB:
        return LRESULT.ZERO;
      case SWT.CR:
        if (!ignoreDefaultSelection) {
          postEvent(DefaultSelection);
        }
        ignoreDefaultSelection = false;
      case SWT.ESC:
        if ((style & SWT.DROP_DOWN) != 0) {
          if (OS.SendMessage(handle, CB_GETDROPPEDSTATE, 0, 0) == 0) {
            return LRESULT.ZERO;
          }
        }
    }
    return result;
  }
}
