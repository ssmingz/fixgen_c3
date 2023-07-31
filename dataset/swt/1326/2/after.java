class PlaceHold {
  public int getItemHeight() {
    checkWidget();
    return ((int) (OS.SendMessage(handle, TVM_GETITEMHEIGHT, 0, 0)));
  }
}
