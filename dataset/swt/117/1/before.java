class PlaceHold {
  public void setExpanded(boolean expanded) {
    checkWidget();
    int path = OS.gtk_tree_model_get_path(parent.modelHandle, handle);
    if (expanded) {
      OS.g_signal_handlers_block_matched(
          parent.handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, ROW_EXPANDED);
      OS.gtk_tree_view_expand_row(parent.handle, path, false);
      OS.g_signal_handlers_unblock_matched(
          parent.handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, ROW_EXPANDED);
    } else {
      OS.g_signal_handlers_block_matched(
          parent.handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, ROW_COLLAPSED);
      OS.gtk_tree_view_collapse_row(parent.handle, path);
      OS.g_signal_handlers_unblock_matched(
          parent.handle, G_SIGNAL_MATCH_DATA, 0, 0, 0, 0, ROW_COLLAPSED);
    }
    OS.gtk_tree_path_free(path);
  }
}
