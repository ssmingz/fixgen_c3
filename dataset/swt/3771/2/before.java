class PlaceHold {
  void showItem(long path, boolean scroll) {
    int depth = OS.gtk_tree_path_get_depth(path);
    if (depth > 1) {
      int[] indices = new int[depth - 1];
      long indicesPtr = OS.gtk_tree_path_get_indices(path);
      OS.memmove(indices, indicesPtr, indices.length * 4);
      long tempPath = OS.gtk_tree_path_new();
      for (int i = 0; i < indices.length; i++) {
        OS.gtk_tree_path_append_index(tempPath, indices[i]);
        OS.gtk_tree_view_expand_row(handle, tempPath, false);
      }
      OS.gtk_tree_path_free(tempPath);
    }
    if (scroll) {
      OS.gtk_widget_realize(handle);
      GdkRectangle cellRect = new GdkRectangle();
      OS.gtk_tree_view_get_cell_area(handle, path, 0, cellRect);
      boolean isHidden = (cellRect.y == 0) && (cellRect.height == 0);
      int[] tx = new int[1];
      int[] ty = new int[1];
      if (OS.GTK_VERSION >= OS.VERSION(2, 12, 0)) {
        OS.gtk_tree_view_convert_widget_to_bin_window_coords(
            handle, cellRect.x, cellRect.y, tx, ty);
      } else {
        OS.gtk_tree_view_widget_to_tree_coords(handle, cellRect.x, cellRect.y, tx, ty);
      }
      GdkRectangle visibleRect = new GdkRectangle();
      OS.gtk_tree_view_get_visible_rect(handle, visibleRect);
      if (!isHidden) {
        if ((ty[0] < visibleRect.y)
            || ((ty[0] + cellRect.height) > (visibleRect.y + visibleRect.height))) {
          isHidden = true;
        }
      }
      if (isHidden) {
        if (depth != 1) {
          OS.gtk_tree_view_scroll_to_cell(handle, path, 0, true, 0.5F, 0.0F);
        } else if (ty[0] < visibleRect.y) {
          OS.gtk_tree_view_scroll_to_point(handle, -1, ty[0]);
        } else {
          int height = Math.min(visibleRect.height, cellRect.height);
          if ((ty[0] + height) > (visibleRect.y + visibleRect.height)) {
            OS.gtk_tree_view_scroll_to_point(
                handle, -1, (ty[0] + cellRect.height) - visibleRect.height);
          }
        }
      }
    }
  }
}
