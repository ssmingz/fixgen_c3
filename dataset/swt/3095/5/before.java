class PlaceHold {
  public void removeAll() {
    checkWidget();
    if (OS.RemoveDataBrowserItems(handle, kDataBrowserNoItem, 0, null, 0) != OS.noErr) {
      error(ERROR_ITEM_NOT_REMOVED);
    }
    for (int i = 0; i < items.length; i++) {
      TreeItem item = items[i];
      if ((item != null) && (!item.isDisposed())) {
        item.releaseResources();
      }
    }
    items = new TreeItem[4];
    anchorFirst = anchorLast = 0;
  }
}
