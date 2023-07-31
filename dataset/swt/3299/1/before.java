class PlaceHold {
  public void select(int start, int end) {
    checkWidget();
    if ((start < 0) && (end < 0)) {
      return;
    }
    int count = OS.gtk_tree_model_iter_n_children(modelHandle, 0);
    if ((start >= count) && (end >= count)) {
      return;
    }
    start = Math.min(count - 1, Math.max(0, start));
    end = Math.min(count - 1, Math.max(0, end));
    int iter = OS.g_malloc(OS.GtkTreeIter_sizeof());
    int selection = OS.gtk_tree_view_get_selection(handle);
    OS.g_signal_handlers_block_matched(selection, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
    for (int index = start; index <= end; index++) {
      OS.gtk_tree_model_iter_nth_child(modelHandle, iter, 0, index);
      OS.gtk_tree_selection_select_iter(selection, iter);
    }
    OS.g_signal_handlers_unblock_matched(selection, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
    OS.g_free(iter);
  }
}
