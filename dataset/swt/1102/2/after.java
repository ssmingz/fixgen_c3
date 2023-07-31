class PlaceHold {
  public TreeItem getParentItem() {
    checkWidget();
    int data = OS.g_list_nth_data(handle, 0);
    GtkCTreeRow row = new GtkCTreeRow(data);
    if (row.parent == 0) {
      return null;
    }
    int ctree = parent.handle;
    int index = OS.gtk_ctree_node_get_row_data(ctree, row.parent) - 1;
    return parent.items[index];
  }
}
