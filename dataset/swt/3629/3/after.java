class PlaceHold {
  public void showItem(TreeItem item) {
    checkWidget();
    if (item == null) {
      error(ERROR_NULL_ARGUMENT);
    }
    if (item.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    int node = item.handle;
    int visibility = OS.gtk_ctree_node_is_visible(handle, node);
    if (visibility != OS.GTK_VISIBILITY_NONE) {
      return;
    }
    if (!OS.gtk_ctree_is_viewable(handle, node)) {
      int parent = node;
      GtkCTreeRow row;
      OS.gtk_signal_handler_block_by_data(handle, Expand);
      do {
        int data = OS.g_list_nth_data(parent, 0);
        row = new GtkCTreeRow();
        OS.memmove(row, data, sizeof);
        if ((parent = row.parent) == 0) {
          break;
        }
        OS.gtk_ctree_expand(handle, parent);
      } while (true);
      OS.gtk_signal_handler_unblock_by_data(handle, Expand);
    }
    OS.gtk_ctree_node_moveto(handle, node, 0, 0.0F, 0.0F);
  }
}
