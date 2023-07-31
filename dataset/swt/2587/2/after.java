class PlaceHold {
  public void setImage(int index, Image image) {
    checkWidget();
    if ((image != null) && image.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    int itemIndex = parent.indexOf(this);
    if (itemIndex == (-1)) {
      return;
    }
    if (index == 0) {
      if ((image != null) && (image.type == SWT.ICON)) {
        if (image.equals(this.image)) {
          return;
        }
      }
      super.setImage(image);
    }
    int hwnd = parent.handle;
    LVITEM lvItem = new LVITEM();
    lvItem.mask = OS.LVIF_IMAGE;
    lvItem.iItem = itemIndex;
    lvItem.iSubItem = index;
    lvItem.iImage = parent.imageIndex(image);
    OS.SendMessage(hwnd, LVM_SETITEM, 0, lvItem);
    if (index == 0) {
      parent.setScrollWidth();
    }
    parent.fixCheckboxImageList();
  }
}
