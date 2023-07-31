class PlaceHold {
  int getItemCount(int node) {
    int depth = 1;
    if (node != 0) {
      int data = OS.g_list_nth_data(node, 0);
      GtkCTreeRow row = new GtkCTreeRow(data);
      depth = row.level + 1;
    }
    Count = 0;
    Callback GtkCTreeCountItems = new Callback(this, "GtkCTreeCountItems", 3);
    int address0 = GtkCTreeCountItems.getAddress();
    OS.gtk_ctree_post_recursive_to_depth(handle, node, depth, address0, node);
    GtkCTreeCountItems.dispose();
    return Count;
  }
}
