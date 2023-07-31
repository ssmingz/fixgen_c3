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
    OS.ItemCollection_Clear(items);
    OS.GCHandle_Free(items);
  }
}
