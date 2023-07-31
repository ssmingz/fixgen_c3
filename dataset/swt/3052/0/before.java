class PlaceHold {
  public int getItemHeight() {
    checkWidget();
    int empty = OS.SendMessage(handle, LVM_APPROXIMATEVIEWRECT, 0, 0);
    int oneItem = OS.SendMessage(handle, LVM_APPROXIMATEVIEWRECT, 1, 0);
    return OS.HIWORD(oneItem) - OS.HIWORD(empty);
  }
}
