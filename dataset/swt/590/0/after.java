class PlaceHold {
  LRESULT WM_CHAR(int wParam, int lParam) {
    if (ignoreCharacter) {
      return null;
    }
    LRESULT result = super.WM_CHAR(wParam, lParam);
    if (result != null) {
      return result;
    }
    switch (((int) (wParam))) {
      case SWT.DEL:
        if (OS.GetKeyState(VK_CONTROL) < 0) {
          return LRESULT.ZERO;
        }
    }
    if ((style & SWT.SINGLE) != 0) {
      switch (((int) (wParam))) {
        case SWT.CR:
          sendSelectionEvent(DefaultSelection);
        case SWT.TAB:
        case SWT.ESC:
          return LRESULT.ZERO;
      }
    }
    return result;
  }
}
