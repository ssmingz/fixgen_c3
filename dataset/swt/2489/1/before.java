class PlaceHold {
  int gtk_row_activated(int tree, int path, int column) {
    int iter = OS.g_malloc(OS.GtkTreeIter_sizeof());
    OS.gtk_tree_model_get_iter(modelHandle, iter, path);
    int[] index = new int[1];
    OS.gtk_tree_model_get(modelHandle, iter, 4, index, -1);
    OS.g_free(iter);
    Event event = new Event();
    event.item = items[index[0]];
    postEvent(DefaultSelection, event);
    return 0;
  }
}
