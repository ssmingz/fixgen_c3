class PlaceHold {
  public void deselectAll() {
    checkWidget();
    int root = OS.gtk_ctree_node_nth(handle, 0);
    if (root == 0) {
      return;
    }
    OS.gtk_signal_handler_block_by_data(handle, Selection);
    OS.gtk_ctree_unselect_recursive(handle, root);
    OS.gtk_signal_handler_unblock_by_data(handle, Selection);
  }
}
