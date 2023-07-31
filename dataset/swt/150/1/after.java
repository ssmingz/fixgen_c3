class PlaceHold {
  int gtk_expose_event(int widget, int eventPtr) {
    if ((state & OBSCURED) != 0) {
      return 0;
    }
    if (((state & PARENT_BACKGROUND) != 0) || (backgroundImage != null)) {
      Control control = findBackgroundControl();
      if (control != null) {
        GdkEventExpose gdkEvent = new GdkEventExpose();
        OS.memmove(gdkEvent, eventPtr, sizeof);
        int window = OS.gtk_tree_view_get_bin_window(handle);
        if (window == gdkEvent.window) {
          int[] width = new int[1];
          int[] height = new int[1];
          gdk_window_get_size(window, width, height);
          int parent = 0;
          int itemCount = OS.gtk_tree_model_iter_n_children(modelHandle, parent);
          GdkRectangle rect = new GdkRectangle();
          boolean expanded = true;
          while (((itemCount != 0) && expanded) && (height[0] > (rect.y + rect.height))) {
            int iter = OS.g_malloc(OS.GtkTreeIter_sizeof());
            OS.gtk_tree_model_iter_nth_child(modelHandle, iter, parent, itemCount - 1);
            itemCount = OS.gtk_tree_model_iter_n_children(modelHandle, iter);
            int path = OS.gtk_tree_model_get_path(modelHandle, iter);
            OS.gtk_tree_view_get_cell_area(handle, path, 0, rect);
            expanded = OS.gtk_tree_view_row_expanded(handle, path);
            OS.gtk_tree_path_free(path);
            if (parent != 0) {
              OS.g_free(parent);
            }
            parent = iter;
          }
          if (parent != 0) {
            OS.g_free(parent);
          }
          if (height[0] > (rect.y + rect.height)) {
            drawBackground(
                control,
                window,
                gdkEvent.region,
                0,
                rect.y + rect.height,
                width[0],
                height[0] - (rect.y + rect.height));
          }
        }
      }
    }
    return super.gtk_expose_event(widget, eventPtr);
  }
}
