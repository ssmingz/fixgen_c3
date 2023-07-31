class PlaceHold {
  void createItem(TabItem item, int index) {
    long list = OS.gtk_container_get_children(handle);
    int itemCount = 0;
    if (list != 0) {
      itemCount = OS.g_list_length(list);
      OS.g_list_free(list);
    }
    if (!((0 <= index) && (index <= itemCount))) {
      error(ERROR_INVALID_RANGE);
    }
    if (itemCount == items.length) {
      TabItem[] newItems = new TabItem[items.length + 4];
      System.arraycopy(items, 0, newItems, 0, items.length);
      items = newItems;
    }
    long boxHandle = gtk_box_new(GTK_ORIENTATION_HORIZONTAL, false, 0);
    if (boxHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    long labelHandle = OS.gtk_label_new_with_mnemonic(null);
    if (labelHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    long imageHandle = OS.gtk_image_new();
    if (imageHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_container_add(boxHandle, imageHandle);
    OS.gtk_container_add(boxHandle, labelHandle);
    long pageHandle = OS.g_object_new(display.gtk_fixed_get_type(), 0);
    if (pageHandle == 0) {
      error(ERROR_NO_HANDLES);
    }
    if (OS.GTK3) {
      OS.gtk_widget_override_background_color(pageHandle, 0, new GdkRGBA());
      long region = OS.gdk_region_new();
      OS.gtk_widget_input_shape_combine_region(pageHandle, region);
      OS.gdk_region_destroy(region);
    }
    OS.g_signal_handlers_block_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, SWITCH_PAGE);
    OS.gtk_notebook_insert_page(handle, pageHandle, boxHandle, index);
    OS.g_signal_handlers_unblock_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, SWITCH_PAGE);
    OS.gtk_widget_show(boxHandle);
    OS.gtk_widget_show(labelHandle);
    OS.gtk_widget_show(pageHandle);
    item.state |= HANDLE;
    item.handle = boxHandle;
    item.labelHandle = labelHandle;
    item.imageHandle = imageHandle;
    item.pageHandle = pageHandle;
    System.arraycopy(items, index, items, index + 1, (itemCount++) - index);
    items[index] = item;
    if ((state & FOREGROUND) != 0) {
      item.setForegroundColor(getForegroundColor());
    }
    if ((state & FONT) != 0) {
      item.setFontDescription(getFontDescription());
    }
    if (itemCount == 1) {
      OS.g_signal_handlers_block_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, SWITCH_PAGE);
      OS.gtk_notebook_set_current_page(handle, 0);
      OS.g_signal_handlers_unblock_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, SWITCH_PAGE);
      Event event = new Event();
      event.item = items[0];
      sendSelectionEvent(Selection, event, false);
    }
  }
}
