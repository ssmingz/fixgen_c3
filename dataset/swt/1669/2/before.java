class PlaceHold {
  public void setImage(Image image) {
    checkWidget();
    int index = parent.indexOf(this);
    if (index == (-1)) {
      return;
    }
    super.setImage(image);
    int hwnd = parent.handle;
    LVITEM lvItem = new LVITEM();
    lvItem.mask = OS.LVIF_IMAGE;
    lvItem.iItem = index;
    lvItem.iImage = parent.imageIndex(image);
    if (OS.SendMessage(hwnd, LVM_SETITEM, 0, lvItem) != 0) {
      parent.setScrollWidth();
    }
  }
}
