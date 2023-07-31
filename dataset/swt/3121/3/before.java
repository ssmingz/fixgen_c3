class PlaceHold {
  public void setTopItem(TreeItem item) {
    checkWidget();
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (item.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    showItem(item, false);
    int columnId = (columnCount == 0) ? column_id : columns[0].id;
    OS.RevealDataBrowserItem(
        handle, item.id, columnId, ((byte) (kDataBrowserRevealWithoutSelecting)));
    Rect rect = new Rect();
    if (OS.GetDataBrowserItemPartBounds(
            handle, item.id, column_id, kDataBrowserPropertyEnclosingPart, rect)
        == OS.noErr) {
      int border = getBorder();
      int[] top = new int[1];
      int[] left = new int[1];
      OS.GetDataBrowserScrollPosition(handle, top, left);
      OS.SetDataBrowserScrollPosition(
          handle, Math.max(0, ((top[0] + rect.top) - border) - getHeaderHeight()), left[0]);
    }
  }
}
