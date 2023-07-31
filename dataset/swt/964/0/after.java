class PlaceHold {
  void destroyItem(MenuItem item) {
    if ((style & SWT.DROP_DOWN) != 0) {
      OS.CompositeCollection_Remove(handle, item.handle);
      if (itemCount == 1) {
        int defaultItem = OS.gcnew_MenuItem();
        OS.CompositeCollection_Insert(handle, 0, defaultItem);
        OS.GCHandle_Free(defaultItem);
      }
    } else {
      int items = OS.ItemsControl_Items(handle);
      OS.ItemCollection_Remove(items, item.handle);
      OS.GCHandle_Free(items);
    }
    itemCount--;
  }
}
