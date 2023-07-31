class PlaceHold {
  public int getWidth() {
    checkWidget();
    int index = parent.indexOf(this);
    if (index == (-1)) {
      return 0;
    }
    int hwnd = parent.handle;
    return ((int) (OS.SendMessage(hwnd, LVM_GETCOLUMNWIDTH, index, 0)));
  }
}
