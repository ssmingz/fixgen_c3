class PlaceHold {
  public TableItem getItem(Point pt) {
    checkWidget();
    int[] path = new int[1];
    int[] column = new int[1];
    int clientY = pt.y - getHeaderHeight();
    if (!OS.gtk_tree_view_get_path_at_pos(handle, pt.x, clientY, path, column, null, null)) {
      return null;
    }
    if (path[0] == 0) {
      return null;
    }
    int indexPtr = OS.gtk_tree_path_get_indices(path[0]);
    OS.gtk_tree_path_free(path[0]);
    if (indexPtr == 0) {
      return null;
    }
    int[] indices = new int[1];
    OS.memmove(indices, indexPtr, 4);
    return items[indices[0]];
  }
}
