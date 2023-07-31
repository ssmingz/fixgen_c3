class PlaceHold {
  public void select(int index) {
    checkWidget();
    if ((index < 0) || (index >= itemCount)) {
      return;
    }
    int selection = OS.gtk_tree_view_get_selection(handle);
    OS.g_signal_handlers_block_matched(selection, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
    OS.gtk_tree_selection_select_iter(selection, items[index].handle);
    if ((style & SWT.SINGLE) != 0) {
      int path = OS.gtk_tree_model_get_path(modelHandle, items[index].handle);
      OS.gtk_tree_view_set_cursor(handle, path, 0, false);
      OS.gtk_tree_path_free(path);
    }
    OS.g_signal_handlers_unblock_matched(selection, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
  }
}
