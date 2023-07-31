class PlaceHold {
  public int getSelectionCount() {
    checkWidget();
    return OS.SendMessage(handle, LVM_GETSELECTEDCOUNT, 0, 0);
  }
}
