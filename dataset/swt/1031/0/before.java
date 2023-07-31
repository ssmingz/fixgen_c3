class PlaceHold {
  public int getPageIncrement() {
    checkWidget();
    return OS.SendMessage(handle, TBM_GETPAGESIZE, 0, 0);
  }
}
