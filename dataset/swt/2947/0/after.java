class PlaceHold {
  int gtk_test_expand_row(int tree, int iter, int path) {
    int[] index = new int[1];
    OS.gtk_tree_model_get(modelHandle, iter, ID_COLUMN, index, -1);
    TreeItem item = items[index[0]];
    Event event = new Event();
    event.item = item;
    boolean oldModelChanged = modelChanged;
    modelChanged = false;
    sendEvent(Expand, event);
    boolean changed = modelChanged || OS.gtk_tree_view_row_expanded(handle, path);
    modelChanged = oldModelChanged;
    if (isDisposed() || item.isDisposed()) {
      return 1;
    }
    if (changed || expandAll) {
      OS.g_signal_handlers_block_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, TEST_EXPAND_ROW);
      OS.gtk_tree_view_expand_row(handle, path, false);
      OS.g_signal_handlers_unblock_matched(
          handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, TEST_EXPAND_ROW);
      return 1;
    }
    return 0;
  }
}
