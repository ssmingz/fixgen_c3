class PlaceHold {
  public void setSelection(int value) {
    checkWidget();
    OS.SendMessage(handle, PBM_SETPOS, value, 0);
    if ((!OS.IsWinCE) && (OS.WIN32_VERSION >= OS.VERSION(6, 0))) {
      int state = OS.SendMessage(handle, PBM_GETSTATE, 0, 0);
      if (state != OS.PBST_NORMAL) {
        OS.SendMessage(handle, PBM_SETPOS, value, 0);
      }
    }
  }
}
