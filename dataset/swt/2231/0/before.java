class PlaceHold {
  LRESULT wmKillFocus(int hwnd, int wParam, int lParam) {
    int value = getSelectionText();
    setSelection(value, true);
    return super.wmKillFocus(hwnd, wParam, lParam);
  }
}
