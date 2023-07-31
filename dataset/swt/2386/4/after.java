class PlaceHold {
  public void removeAll() {
    checkWidget();
    for (int i = 0; i < items.length; i++) {
      TreeItem item = items[i];
      if ((item != null) && (!item.isDisposed())) {
        item.release(false);
      }
    }
    items = new TreeItem[4];
    ignoreExpand = true;
    if (OS.RemoveDataBrowserItems(handle, kDataBrowserNoItem, 0, null, 0) != OS.noErr) {
      error(ERROR_ITEM_NOT_REMOVED);
    }
    ignoreExpand = false;
    OS.SetDataBrowserScrollPosition(handle, 0, 0);
    anchorFirst = anchorLast = 0;
    setScrollWidth();
  }
}
