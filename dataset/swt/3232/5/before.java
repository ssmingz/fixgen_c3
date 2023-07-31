class PlaceHold {
  void createItem(TableItem item, int index) {
    if (!((0 <= index) && (index <= itemCount))) {
      error(ERROR_ITEM_NOT_ADDED);
    }
    if (itemCount == items.length) {
      TableItem[] newItems = new TableItem[items.length + 4];
      System.arraycopy(items, 0, newItems, 0, items.length);
      items = newItems;
    }
    item.handle = OS.g_malloc(OS.GtkTreeIter_sizeof());
    if (item.handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_list_store_insert(modelHandle, item.handle, index);
    System.arraycopy(items, index, items, index + 1, (itemCount++) - index);
    items[index] = item;
  }
}
