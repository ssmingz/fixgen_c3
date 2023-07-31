class PlaceHold {
  public boolean getEnabled() {
    checkWidget();
    int hwnd = parent.handle;
    int fsState = OS.SendMessage(hwnd, TB_GETSTATE, id, 0);
    return (fsState & OS.TBSTATE_ENABLED) != 0;
  }
}
