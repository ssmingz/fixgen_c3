class PlaceHold {
  public int getMaximum() {
    checkWidget();
    return OS.SendMessage(handle, PBM_GETRANGE, 0, 0);
  }
}
