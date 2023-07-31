class PlaceHold {
  void redraw() {
    int parentHandle = parent.handle;
    if (gtk_widget_get_realized(parentHandle)) {
      int path = OS.gtk_tree_model_get_path(parent.modelHandle, handle);
      GdkRectangle rect = new GdkRectangle();
      OS.gtk_tree_view_get_cell_area(parentHandle, path, 0, rect);
      OS.gtk_tree_path_free(path);
      int window = OS.gtk_tree_view_get_bin_window(parentHandle);
      rect.x = 0;
      int[] w = new int[1];
      int[] h = new int[1];
      OS.gdk_drawable_get_size(window, w, h);
      rect.width = w[0];
      OS.gdk_window_invalidate_rect(window, rect, false);
    }
  }
}
