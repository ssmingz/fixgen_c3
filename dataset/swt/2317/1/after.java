class PlaceHold {
  public int getMaximum() {
    checkWidget();
    return ((int) (OS.SendMessage(handle, PBM_GETRANGE, 0, 0)));
  }
}
