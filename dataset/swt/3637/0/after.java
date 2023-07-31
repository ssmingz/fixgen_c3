class PlaceHold {
  int processToggle(int pathString, int handle) {
    int path = OS.gtk_tree_path_new_from_string(pathString);
    int iter = OS.g_malloc(OS.GtkTreeIter_sizeof());
    OS.gtk_tree_model_get_iter(modelHandle, iter, path);
    int[] index = new int[1];
    OS.gtk_tree_model_get(modelHandle, iter, 4, index, -1);
    OS.g_free(iter);
    OS.gtk_tree_path_free(path);
    boolean checked = items[index[0]].getChecked();
    checked = !checked;
    items[index[0]].setChecked(checked);
    Event event = new Event();
    event.detail = SWT.CHECK;
    event.item = items[index[0]];
    postEvent(Selection, event);
    return 0;
  }
}
