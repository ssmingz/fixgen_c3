class PlaceHold {
  public TreeItem getParentItem() {
    checkWidget();
    int path = OS.gtk_tree_model_get_path(parent.modelHandle, handle);
    if (OS.gtk_tree_path_get_depth(path) < 2) {
      OS.gtk_tree_path_free(path);
      return null;
    }
    OS.gtk_tree_path_up(path);
    int iter = OS.g_malloc(OS.GtkTreeIter_sizeof());
    OS.gtk_tree_model_get_iter(parent.modelHandle, iter, path);
    int[] index = new int[1];
    OS.gtk_tree_model_get(parent.modelHandle, iter, 4, index, -1);
    OS.g_free(iter);
    OS.gtk_tree_path_free(path);
    return parent.items[index[0]];
  }
}
