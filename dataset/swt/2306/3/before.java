class PlaceHold {
  LRESULT wmKillFocus(int hwnd, int wParam, int lParam) {
    int value = getSelectionText();
    if (value == (-1)) {
      if (OS.IsWinCE) {
        value = OS.SendMessage(hwndUpDown, UDM_GETPOS, 0, 0) & 0xffff;
      } else {
        value = OS.SendMessage(hwndUpDown, UDM_GETPOS32, 0, 0);
      }
      setSelection(value, false, true, false);
    }
    return super.wmKillFocus(hwnd, wParam, lParam);
  }
}
