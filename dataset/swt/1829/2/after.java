class PlaceHold {
  public void removeAll() {
    checkWidget();
    hFirstIndexOf = hLastIndexOf = 0;
    itemCount = -1;
    for (int i = 0; i < items.length; i++) {
      TreeItem item = items[i];
      if ((item != null) && (!item.isDisposed())) {
        item.release(false);
      }
    }
    ignoreDeselect = ignoreSelect = true;
    boolean redraw = getDrawing() && OS.IsWindowVisible(handle);
    if (redraw) {
      OS.DefWindowProc(handle, WM_SETREDRAW, 0, 0);
    }
    shrink = ignoreShrink = true;
    long result = OS.SendMessage(handle, TVM_DELETEITEM, 0, TVI_ROOT);
    ignoreShrink = false;
    if (redraw) {
      OS.DefWindowProc(handle, WM_SETREDRAW, 1, 0);
      OS.InvalidateRect(handle, null, true);
    }
    ignoreDeselect = ignoreSelect = false;
    if (result == 0) {
      error(ERROR_ITEM_NOT_REMOVED);
    }
    if (imageList != null) {
      OS.SendMessage(handle, TVM_SETIMAGELIST, 0, 0);
      display.releaseImageList(imageList);
    }
    imageList = null;
    if ((hwndParent == 0) && (!linesVisible)) {
      if (((!hooks(MeasureItem)) && (!hooks(EraseItem))) && (!hooks(PaintItem))) {
        customDraw = false;
      }
    }
    hAnchor = hInsert = hFirstIndexOf = hLastIndexOf = 0;
    itemCount = -1;
    items = new TreeItem[4];
    scrollWidth = 0;
    setScrollWidth();
    updateScrollBar();
  }
}
