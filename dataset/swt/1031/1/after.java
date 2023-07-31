class PlaceHold {
  public int getIncrement() {
    checkWidget();
    return ((int) (OS.SendMessage(handle, TBM_GETLINESIZE, 0, 0)));
  }
}
