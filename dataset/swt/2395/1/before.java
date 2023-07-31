class PlaceHold {
  void createItem(TableItem item, int index) {
    if (!((0 <= index) && (index <= itemCount))) {
      error(ERROR_INVALID_RANGE);
    }
    if (itemCount == items.length) {
      int newLength = (drawCount == 0) ? items.length + 4 : (items.length * 3) / 2;
      TableItem[] newItems = new TableItem[newLength];
      System.arraycopy(items, 0, newItems, 0, items.length);
      items = newItems;
    }
    item.handle = OS.g_malloc(OS.GtkTreeIter_sizeof());
    if (item.handle == 0) {
      error(ERROR_NO_HANDLES);
    }
    if (index == itemCount) {
      OS.gtk_list_store_append(modelHandle, item.handle);
    } else {
      OS.gtk_list_store_insert(modelHandle, item.handle, index);
    }
    System.arraycopy(items, index, items, index + 1, (itemCount++) - index);
    items[index] = item;
  }
}
