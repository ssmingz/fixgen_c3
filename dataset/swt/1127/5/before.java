class PlaceHold {
  void destroyItem(TreeItem item) {
    int hItem = item.handle;
    TVITEM tvItem = new TVITEM();
    tvItem.mask = OS.TVIF_HANDLE | OS.TVIF_PARAM;
    releaseItems(new TreeItem[] {item}, tvItem);
    boolean fixRedraw = false;
    if ((drawCount == 0) && OS.IsWindowVisible(handle)) {
      RECT rect = new RECT();
      rect.left = hItem;
      fixRedraw = OS.SendMessage(handle, TVM_GETITEMRECT, 0, rect) == 0;
    }
    if (fixRedraw) {
      OS.UpdateWindow(handle);
      OS.SendMessage(handle, WM_SETREDRAW, 0, 0);
    }
    OS.SendMessage(handle, TVM_DELETEITEM, 0, hItem);
    if (fixRedraw) {
      OS.SendMessage(handle, WM_SETREDRAW, 1, 0);
      OS.ValidateRect(handle, null);
    }
    int count = OS.SendMessage(handle, TVM_GETCOUNT, 0, 0);
    if (count == 0) {
      if (imageList != null) {
        OS.SendMessage(handle, TVM_SETIMAGELIST, 0, 0);
        Display display = getDisplay();
        display.releaseImageList(imageList);
      }
      imageList = null;
      items = new TreeItem[4];
    }
  }
}
