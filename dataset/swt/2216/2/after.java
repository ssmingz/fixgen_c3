class PlaceHold {
  int gtk_row_activated(int tree, int path, int column) {
    TableItem item = null;
    int indices = OS.gtk_tree_path_get_indices(path);
    if (indices != 0) {
      int[] index = new int[] {-1};
      OS.memmove(index, indices, 4);
      item = _getItem(index[0]);
    }
    Event event = new Event();
    event.item = item;
    sendSelectionEvent(DefaultSelection, event, false);
    return 0;
  }
}
