class PlaceHold {
  void selectFocusIndex(int index) {
    int count = OS.gtk_tree_model_iter_n_children(modelHandle, 0);
    if (!((0 <= index) && (index < count))) {
      return;
    }
    int iter = OS.g_malloc(OS.GtkTreeIter_sizeof());
    OS.gtk_tree_model_iter_nth_child(modelHandle, iter, 0, index);
    int path = OS.gtk_tree_model_get_path(modelHandle, iter);
    int selection = OS.gtk_tree_view_get_selection(handle);
    OS.g_signal_handlers_block_matched(selection, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
    OS.gtk_tree_view_set_cursor(handle, path, 0, false);
    OS.gtk_tree_selection_select_iter(selection, iter);
    OS.g_signal_handlers_unblock_matched(selection, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
    OS.gtk_tree_path_free(path);
    OS.g_free(iter);
  }
}
