class PlaceHold {
  public void remove(int start, int end) {
    checkWidget();
    if (start > end) {
      return;
    }
    if (!(((0 <= start) && (start <= end)) && (end < itemCount))) {
      error(ERROR_INVALID_RANGE);
    }
    int selection = OS.gtk_tree_view_get_selection(handle);
    int iter = OS.g_malloc(OS.GtkTreeIter_sizeof());
    OS.gtk_tree_model_iter_nth_child(modelHandle, iter, 0, start);
    int index = start;
    while (index <= end) {
      TableItem item = items[index];
      if ((item != null) && (!item.isDisposed())) {
        item.releaseChildren(false);
      }
      OS.g_signal_handlers_block_matched(selection, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
      OS.gtk_list_store_remove(modelHandle, iter);
      OS.g_signal_handlers_unblock_matched(selection, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
      index++;
    }
    OS.g_free(iter);
    System.arraycopy(items, index, items, start, itemCount - index);
    for (int i = itemCount - (index - start); i < itemCount; i++) {
      items[i] = null;
    }
    itemCount = itemCount - (index - start);
  }
}
