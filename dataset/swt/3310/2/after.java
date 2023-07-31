class PlaceHold {
  int GtkCTreeDispose(int ctree, int node, int data) {
    int index = OS.gtk_ctree_node_get_row_data(ctree, node) - 1;
    OS.gtk_ctree_node_set_row_data(ctree, node, 0);
    TreeItem item = items[index];
    if ((item != null) && (!item.isDisposed())) {
      item.releaseResources();
    }
    items[index] = null;
    return 0;
  }
}
