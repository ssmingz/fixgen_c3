class PlaceHold {
  public int getPageIncrement() {
    checkWidget();
    return ((int) (OS.SendMessage(handle, TBM_GETPAGESIZE, 0, 0)));
  }
}
