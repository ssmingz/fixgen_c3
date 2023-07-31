class PlaceHold {
  public void setMaximum(int value) {
    checkWidget();
    if (value < 0) {
      return;
    }
    int[] min = new int[1];
    OS.SendMessage(hwndUpDown, UDM_GETRANGE32, min, null);
    if (value <= min[0]) {
      return;
    }
    int pos;
    if (OS.IsWinCE) {
      pos = OS.LOWORD(OS.SendMessage(hwndUpDown, UDM_GETPOS, 0, 0));
    } else {
      pos = ((int) (OS.SendMessage(hwndUpDown, UDM_GETPOS32, 0, 0)));
    }
    OS.SendMessage(hwndUpDown, UDM_SETRANGE32, min[0], value);
    if (pos > value) {
      setSelection(value, true, true, false);
    }
  }
}
