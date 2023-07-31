class PlaceHold {
  public void setMinimum(int value) {
    checkWidget();
    if (value < 0) {
      return;
    }
    int[] max = new int[1];
    OS.SendMessage(hwndUpDown, UDM_GETRANGE32, null, max);
    if (value >= max[0]) {
      return;
    }
    int pos;
    if (OS.IsWinCE) {
      pos = OS.SendMessage(hwndUpDown, UDM_GETPOS, 0, 0) & 0xffff;
    } else {
      pos = OS.SendMessage(hwndUpDown, UDM_GETPOS32, 0, 0);
    }
    OS.SendMessage(hwndUpDown, UDM_SETRANGE32, value, max[0]);
    if (pos < value) {
      setSelection(value, true, true, false);
    }
  }
}
