class PlaceHold {
  public void select(int index) {
    checkWidget();
    if (!((0 <= index) && (index < OS.gtk_tree_model_iter_n_children(modelHandle, 0)))) {
      return;
    }
    int iter = OS.g_malloc(OS.GtkTreeIter_sizeof());
    int selection = OS.gtk_tree_view_get_selection(handle);
    OS.g_signal_handlers_block_matched(selection, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
    OS.gtk_tree_model_iter_nth_child(modelHandle, iter, 0, index);
    OS.gtk_tree_selection_select_iter(selection, iter);
    int path = OS.gtk_tree_model_get_path(modelHandle, iter);
    OS.gtk_tree_view_set_cursor(handle, path, 0, false);
    OS.gtk_tree_path_free(path);
    OS.g_signal_handlers_unblock_matched(selection, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
    OS.g_free(iter);
  }
}
