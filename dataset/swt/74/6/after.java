class PlaceHold {
  int gtk_toggled(int renderer, int pathStr) {
    int path = OS.gtk_tree_path_new_from_string(pathStr);
    if (path == 0) {
      return 0;
    }
    int indices = OS.gtk_tree_path_get_indices(path);
    if (indices != 0) {
      int[] index = new int[1];
      OS.memmove(index, indices, 4);
      TableItem item = _getItem(index[0]);
      item.setChecked(!item.getChecked());
      Event event = new Event();
      event.detail = SWT.CHECK;
      event.item = item;
      sendSelectionEvent(Selection, event, false);
    }
    OS.gtk_tree_path_free(path);
    return 0;
  }
}
