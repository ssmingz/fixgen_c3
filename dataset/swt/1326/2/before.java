class PlaceHold {
  public int getItemHeight() {
    checkWidget();
    return OS.SendMessage(handle, TVM_GETITEMHEIGHT, 0, 0);
  }
}
