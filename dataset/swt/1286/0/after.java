class PlaceHold {
  int processMouseDown(int int0, int int1, int int2) {
    GdkEventButton e = new GdkEventButton();
    int indexPtr;
    int[] path = new int[1];
    int[] column = new int[1];
    OS.memmove(e, int0, sizeof);
    if (((e.type == OS.GDK_2BUTTON_PRESS)
            && OS.gtk_tree_view_get_path_at_pos(
                handle, ((int) (e.x)), ((int) (e.y)), path, column, null, null))
        && ((indexPtr = OS.gtk_tree_path_get_indices(path[0])) != 0)) {
      int[] indices = new int[1];
      OS.memmove(indices, indexPtr, 4);
      Event event = new Event();
      event.item = items[indices[0]];
      postEvent(DefaultSelection, event);
      OS.gtk_tree_path_free(path[0]);
    }
    int headerHeight = getHeaderHeight();
    e.y += headerHeight;
    OS.memmove(int0, e, sizeof);
    int result = super.processMouseDown(int0, int1, int2);
    e.y -= headerHeight;
    OS.memmove(int0, e, sizeof);
    return result;
  }
}
