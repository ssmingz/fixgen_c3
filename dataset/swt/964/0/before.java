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
      int itemCollection = OS.ItemsControl_Items(handle);
      OS.ItemCollection_Remove(itemCollection, item.handle);
    }
    itemCount--;
  }
}
