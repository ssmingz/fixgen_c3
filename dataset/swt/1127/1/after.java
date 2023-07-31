class PlaceHold {
  public void removeAll() {
    checkWidget();
    ignoreDeselect = ignoreSelect = true;
    int result = OS.SendMessage(handle, TVM_DELETEITEM, 0, TVI_ROOT);
    ignoreDeselect = ignoreSelect = false;
    if (result == 0) {
      error(ERROR_ITEM_NOT_REMOVED);
    }
    for (int i = 0; i < items.length; i++) {
      TreeItem item = items[i];
      if ((item != null) && (!item.isDisposed())) {
        item.releaseWidget();
        item.releaseHandle();
      }
    }
    if (imageList != null) {
      OS.SendMessage(handle, TVM_SETIMAGELIST, 0, 0);
      Display display = getDisplay();
      display.releaseImageList(imageList);
    }
    imageList = null;
    customDraw = false;
    items = new TreeItem[4];
    hAnchor = 0;
  }
}
