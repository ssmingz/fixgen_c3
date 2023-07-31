class PlaceHold {
  @Override
  long gtk_row_has_child_toggled(long model, long path, long iter) {
    int[] index = new int[1];
    OS.gtk_tree_model_get(modelHandle, iter, ID_COLUMN, index, -1);
    TreeItem item = items[index[0]];
    if (item == null) {
      return 0;
    }
    int childCount = OS.gtk_tree_model_iter_n_children(modelHandle, item.handle);
    if ((childCount != 0) && item.isExpanded) {
      OS.g_signal_handlers_block_matched(handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, TEST_EXPAND_ROW);
      OS.gtk_tree_view_expand_row(handle, path, false);
      OS.g_signal_handlers_unblock_matched(
          handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, TEST_EXPAND_ROW);
    }
    return 0;
  }
}
