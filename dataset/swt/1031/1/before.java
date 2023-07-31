class PlaceHold {
  public int getIncrement() {
    checkWidget();
    return OS.SendMessage(handle, TBM_GETLINESIZE, 0, 0);
  }
}
