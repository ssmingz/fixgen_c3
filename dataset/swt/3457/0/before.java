class PlaceHold {
  void releaseWidget() {
    super.releaseWidget();
    customDraw = false;
    if (imageList != null) {
      OS.SendMessage(handle, TVM_SETIMAGELIST, TVSIL_NORMAL, 0);
      display.releaseImageList(imageList);
    }
    if (headerImageList != null) {
      if (hwndHeader != 0) {
        OS.SendMessage(hwndHeader, HDM_SETIMAGELIST, 0, 0);
      }
      display.releaseImageList(headerImageList);
    }
    imageList = headerImageList = null;
    int hStateList = OS.SendMessage(handle, TVM_GETIMAGELIST, TVSIL_STATE, 0);
    OS.SendMessage(handle, TVM_SETIMAGELIST, TVSIL_STATE, 0);
    if (hStateList != 0) {
      OS.ImageList_Destroy(hStateList);
    }
    if (itemToolTipHandle != 0) {
      OS.DestroyWindow(itemToolTipHandle);
    }
    if (headerToolTipHandle != 0) {
      OS.DestroyWindow(headerToolTipHandle);
    }
    itemToolTipHandle = headerToolTipHandle = 0;
  }
}
