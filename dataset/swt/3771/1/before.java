class PlaceHold {
  public void showSelection() {
    checkWidget();
    int index = getSelectionIndex();
    if (index == (-1)) {
      return;
    }
    long iter = OS.g_malloc(OS.GtkTreeIter_sizeof());
    OS.gtk_tree_model_iter_nth_child(modelHandle, iter, 0, index);
    long path = OS.gtk_tree_model_get_path(modelHandle, iter);
    OS.gtk_widget_realize(handle);
    GdkRectangle visibleRect = new GdkRectangle();
    OS.gtk_tree_view_get_visible_rect(handle, visibleRect);
    GdkRectangle cellRect = new GdkRectangle();
    OS.gtk_tree_view_get_cell_area(handle, path, 0, cellRect);
    int[] tx = new int[1];
    int[] ty = new int[1];
    if (OS.GTK_VERSION >= OS.VERSION(2, 12, 0)) {
      OS.gtk_tree_view_convert_widget_to_bin_window_coords(handle, cellRect.x, cellRect.y, tx, ty);
    } else {
      OS.gtk_tree_view_widget_to_tree_coords(handle, cellRect.x, cellRect.y, tx, ty);
    }
    if (ty[0] < visibleRect.y) {
      OS.gtk_tree_view_scroll_to_cell(handle, path, 0, true, 0.0F, 0.0F);
      OS.gtk_tree_view_scroll_to_point(handle, -1, ty[0]);
    } else {
      int height = Math.min(visibleRect.height, cellRect.height);
      if ((ty[0] + height) > (visibleRect.y + visibleRect.height)) {
        OS.gtk_tree_view_scroll_to_cell(handle, path, 0, true, 1.0F, 0.0F);
        ty[0] += cellRect.height - visibleRect.height;
        OS.gtk_tree_view_scroll_to_point(handle, -1, ty[0]);
      }
    }
    OS.gtk_tree_path_free(path);
    OS.g_free(iter);
  }
}
