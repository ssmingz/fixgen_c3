class PlaceHold {
  public void setDigits(int value) {
    checkWidget();
    if (value < 0) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if (value == this.digits) {
      return;
    }
    this.digits = value;
    int pos;
    if (OS.IsWinCE) {
      pos = OS.LOWORD(OS.SendMessage(hwndUpDown, UDM_GETPOS, 0, 0));
    } else {
      pos = ((int) (OS.SendMessage(hwndUpDown, UDM_GETPOS32, 0, 0)));
    }
    setSelection(pos, false, true, false);
  }
}
