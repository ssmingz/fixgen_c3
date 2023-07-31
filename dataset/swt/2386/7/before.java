class PlaceHold {
  public void removeAll() {
    checkWidget();
    for (int i = 0; i < items.length; i++) {
      TreeItem item = items[i];
      if ((item != null) && (!item.isDisposed())) {
        item.releaseChildren(false);
      }
    }
    items = new TreeItem[4];
    int selection = OS.gtk_tree_view_get_selection(handle);
    OS.g_signal_handlers_block_matched(selection, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
    int oldModel = modelHandle;
    int[] types = getColumnTypes(Math.max(1, columnCount));
    int newModel = OS.gtk_tree_store_newv(types.length, types);
    if (newModel == 0) {
      error(ERROR_NO_HANDLES);
    }
    OS.gtk_tree_view_set_model(handle, newModel);
    OS.g_object_unref(oldModel);
    modelHandle = newModel;
    OS.g_signal_handlers_unblock_matched(selection, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
    if ((style & SWT.VIRTUAL) != 0) {
      OS.gtk_tree_view_set_search_column(handle, -1);
    } else {
      int firstColumn = (columnCount == 0) ? FIRST_COLUMN : columns[0].modelIndex;
      OS.gtk_tree_view_set_search_column(handle, firstColumn + CELL_TEXT);
    }
  }
}
