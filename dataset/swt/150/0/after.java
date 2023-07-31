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
          int bottom = 0;
          if (itemCount != 0) {
            int iter = OS.g_malloc(OS.GtkTreeIter_sizeof());
            OS.gtk_tree_model_iter_nth_child(modelHandle, iter, 0, itemCount - 1);
            int path = OS.gtk_tree_model_get_path(modelHandle, iter);
            GdkRectangle rect = new GdkRectangle();
            OS.gtk_tree_view_get_cell_area(handle, path, 0, rect);
            bottom = rect.y + rect.height;
            OS.gtk_tree_path_free(path);
            OS.g_free(iter);
          }
          if (height[0] > bottom) {
            drawBackground(
                control, window, gdkEvent.region, 0, bottom, width[0], height[0] - bottom);
          }
        }
      }
    }
    return super.gtk_expose_event(widget, eventPtr);
  }
}
