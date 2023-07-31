class PlaceHold {
  public void remove(int index) {
    checkWidget();
    if (!((0 <= index) && (index < itemCount))) {
      error(ERROR_ITEM_NOT_REMOVED);
    }
    int iter = OS.g_malloc(OS.GtkTreeIter_sizeof());
    TableItem item = items[index];
    boolean disposed = false;
    if (item != null) {
      disposed = item.isDisposed();
      if (!disposed) {
        OS.memmove(iter, item.handle, OS.GtkTreeIter_sizeof());
        item.release(false);
      }
    } else {
      OS.gtk_tree_model_iter_nth_child(modelHandle, iter, 0, index);
    }
    if (!disposed) {
      int selection = OS.gtk_tree_view_get_selection(handle);
      OS.g_signal_handlers_block_matched(selection, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
      OS.gtk_list_store_remove(modelHandle, iter);
      OS.g_signal_handlers_unblock_matched(selection, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
      System.arraycopy(items, index + 1, items, index, (--itemCount) - index);
      items[itemCount] = null;
    }
    OS.g_free(iter);
  }
}
