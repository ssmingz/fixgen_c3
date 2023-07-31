class PlaceHold {
  public int getMaximum() {
    checkWidget();
    return OS.SendMessage(handle, TBM_GETRANGEMAX, 0, 0);
  }
}
