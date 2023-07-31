class PlaceHold {
  void createItem(MenuItem item, int index) {
    if (!((0 <= index) && (index <= itemCount))) {
      error(ERROR_INVALID_RANGE);
    }
    item.createWidget();
    if ((style & SWT.DROP_DOWN) != 0) {
      if (itemCount == 0) {
        OS.CompositeCollection_RemoveAt(handle, 0);
      }
      OS.CompositeCollection_Insert(handle, index, item.handle);
    } else {
      int itemCollection = OS.ItemsControl_Items(handle);
      OS.ItemCollection_Insert(itemCollection, index, item.handle);
      OS.GCHandle_Free(itemCollection);
    }
    itemCount++;
  }
}
