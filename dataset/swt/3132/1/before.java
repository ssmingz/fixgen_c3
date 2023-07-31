class PlaceHold {
  void selectFocusIndex(int index) {
    if (!((0 <= index) && (index < itemCount))) {
      return;
    }
    TableItem item = _getItem(index);
    int path = OS.gtk_tree_model_get_path(modelHandle, item.handle);
    int selection = OS.gtk_tree_view_get_selection(handle);
    OS.g_signal_handlers_block_matched(selection, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
    OS.gtk_tree_view_set_cursor(handle, path, 0, false);
    OS.g_signal_handlers_unblock_matched(selection, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, CHANGED);
    OS.gtk_tree_path_free(path);
  }
}
