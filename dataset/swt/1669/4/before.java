class PlaceHold {
  public void setImage(int index, Image image) {
    checkWidget();
    if (index == 0) {
      setImage(image);
      return;
    }
    int itemIndex = parent.indexOf(this);
    if (itemIndex == (-1)) {
      return;
    }
    int hwnd = parent.handle;
    LVITEM lvItem = new LVITEM();
    lvItem.mask = OS.LVIF_IMAGE;
    lvItem.iItem = itemIndex;
    lvItem.iSubItem = index;
    lvItem.iImage = parent.imageIndex(image);
    OS.SendMessage(hwnd, LVM_SETITEM, 0, lvItem);
  }
}
