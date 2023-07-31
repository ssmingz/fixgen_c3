class PlaceHold {
  void createItem(TabItem item, int index) {
    int list = OS.gtk_container_get_children(handle);
    int itemCount = 0;
    if (list != 0) {
      itemCount = OS.g_list_length(list);
      OS.g_list_free(list);
    }
    if (!((0 <= index) && (index <= itemCount))) {
      error(ERROR_ITEM_NOT_ADDED);
    }
    if (itemCount == items.length) {
      TabItem[] newItems = new TabItem[items.length + 4];
      System.arraycopy(items, 0, newItems, 0, items.length);
      items = newItems;
    }
    int labelHandle = OS.gtk_label_new("");
    if (labelHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    int pageHandle = OS.gtk_fixed_new();
    if (pageHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_signal_handler_block_by_data(handle, Selection);
    OS.gtk_notebook_insert_page(handle, pageHandle, labelHandle, index);
    OS.gtk_signal_handler_unblock_by_data(handle, Selection);
    OS.gtk_widget_show(labelHandle);
    OS.gtk_widget_show(pageHandle);
    item.state |= HANDLE;
    item.handle = labelHandle;
    item.pageHandle = pageHandle;
    System.arraycopy(items, index, items, index + 1, (itemCount++) - index);
    items[index] = item;
    item.setForegroundColor(getForegroundColor());
    item.setFontDescription(getFontDescription());
  }
}
