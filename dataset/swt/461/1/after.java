class PlaceHold {
  public void removeAll() {
    checkWidget();
    int items = OS.ItemsControl_Items(handle);
    for (int i = 0; i < itemCount; i++) {
      TreeItem item = parent.getItem(items, i, false);
      if ((item != null) && (!item.isDisposed())) {
        item.release(false);
      }
    }
    parent.ignoreSelection = true;
    OS.ItemCollection_Clear(items);
    parent.ignoreSelection = false;
    OS.GCHandle_Free(items);
  }
}
