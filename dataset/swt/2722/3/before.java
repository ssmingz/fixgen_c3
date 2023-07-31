class PlaceHold {
  public int getSelection() {
    checkWidget();
    if (OS.IsWinCE) {
      return OS.LOWORD(OS.SendMessage(hwndUpDown, UDM_GETPOS, 0, 0));
    } else {
      return OS.SendMessage(hwndUpDown, UDM_GETPOS32, 0, 0);
    }
  }
}
