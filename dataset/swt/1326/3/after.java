class PlaceHold {
  public int getSelectionCount() {
    checkWidget();
    return ((int) (OS.SendMessage(handle, LVM_GETSELECTEDCOUNT, 0, 0)));
  }
}
