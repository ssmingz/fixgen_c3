class PlaceHold {
  public int getMaximum() {
    checkWidget();
    return ((int) (OS.SendMessage(handle, TBM_GETRANGEMAX, 0, 0)));
  }
}
