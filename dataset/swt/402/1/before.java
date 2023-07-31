class PlaceHold {
  int gtk_toggled(int renderer, int pathStr) {
    int path = OS.gtk_tree_path_new_from_string(pathStr);
    if (path == 0) {
      return 0;
    }
    TreeItem item = null;
    int iter = OS.g_malloc(OS.GtkTreeIter_sizeof());
    if (OS.gtk_tree_model_get_iter(modelHandle, iter, path)) {
      item = _getItem(iter);
    }
    OS.g_free(iter);
    OS.gtk_tree_path_free(path);
    if (item != null) {
      item.setChecked(!item.getChecked());
      Event event = new Event();
      event.detail = SWT.CHECK;
      event.item = item;
      postEvent(Selection, event);
    }
    return 0;
  }
}
