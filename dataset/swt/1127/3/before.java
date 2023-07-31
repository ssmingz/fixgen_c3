class PlaceHold {
  void releaseWidget() {
    for (int i = 0; i < items.length; i++) {
      TreeItem item = items[i];
      if ((item != null) && (!item.isDisposed())) {
        item.releaseWidget();
        item.releaseHandle();
      }
    }
    items = null;
    if (imageList != null) {
      OS.SendMessage(handle, TVM_SETIMAGELIST, TVSIL_NORMAL, 0);
      Display display = getDisplay();
      display.releaseImageList(imageList);
    } else {
      int hOldList = OS.SendMessage(handle, TVM_GETIMAGELIST, TVSIL_NORMAL, 0);
      OS.SendMessage(handle, TVM_SETIMAGELIST, TVSIL_NORMAL, 0);
      if (hOldList != 0) {
        OS.ImageList_Destroy(hOldList);
      }
    }
    imageList = null;
    int hOldList = OS.SendMessage(handle, TVM_GETIMAGELIST, TVSIL_STATE, 0);
    OS.SendMessage(handle, TVM_SETIMAGELIST, TVSIL_STATE, 0);
    if (hOldList != 0) {
      OS.ImageList_Destroy(hOldList);
    }
    super.releaseWidget();
  }
}
