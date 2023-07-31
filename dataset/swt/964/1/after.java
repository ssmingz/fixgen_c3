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
      int items = OS.ItemsControl_Items(handle);
      OS.ItemCollection_Insert(items, index, item.handle);
      OS.GCHandle_Free(items);
    }
    itemCount++;
  }
}
