class PlaceHold {
  void resetCustomDraw() {
    int end = Math.max(1, columnCount);
    for (int i = 0; i < end; i++) {
      boolean customDraw = (columnCount != 0) ? columns[i].customDraw : firstCustomDraw;
      if (customDraw) {
        int column = OS.gtk_tree_view_get_column(handle, i);
        int list = OS.gtk_tree_view_column_get_cell_renderers(column);
        int length = OS.g_list_length(list);
        int renderer = OS.g_list_nth_data(list, length - 1);
        OS.g_list_free(list);
        OS.gtk_tree_view_column_set_cell_data_func(column, renderer, 0, 0, 0);
        if (columnCount != 0) {
          columns[i].customDraw = false;
        }
      }
    }
    firstCustomDraw = false;
  }
}
