class PlaceHold {
  public void removeAll() {
    checkWidget();
    for (int i = 0; i < items.length; i++) {
      TreeItem item = items[i];
      if ((item != null) && (!item.isDisposed())) {
        item.release(false);
      }
    }
    items = new TreeItem[4];
    int selection = OS.gtk_tree_view_get_selection(handle);
    OS.g_signal_handlers_block_matched(selection, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
    if (fixAccessibility()) {
      ignoreAccessibility = true;
    }
    OS.gtk_tree_store_clear(modelHandle);
    if (fixAccessibility()) {
      ignoreAccessibility = false;
      OS.g_object_notify(handle, model);
    }
    OS.g_signal_handlers_unblock_matched(selection, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
    if ((style & SWT.VIRTUAL) != 0) {
      if (OS.GTK_VERSION >= OS.VERSION(2, 6, 5)) {
        OS.gtk_tree_view_set_search_column(handle, -1);
      } else {
        OS.gtk_tree_view_set_enable_search(handle, false);
      }
    } else {
      int firstColumn = (columnCount == 0) ? FIRST_COLUMN : columns[0].modelIndex;
      OS.gtk_tree_view_set_search_column(handle, firstColumn + CELL_TEXT);
    }
  }
}
