class PlaceHold {
  LRESULT wmChar(int hwnd, int wParam, int lParam) {
    LRESULT result = super.wmChar(hwnd, wParam, lParam);
    if (result != null) {
      return result;
    }
    switch (wParam) {
      case SWT.CR:
        int value = getSelectionText();
        setSelection(value, true);
        postEvent(DefaultSelection);
      case SWT.TAB:
      case SWT.ESC:
        return LRESULT.ZERO;
    }
    return result;
  }
}
