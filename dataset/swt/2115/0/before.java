class PlaceHold {
  public Rectangle getImageBounds(int index) {
    checkWidget();
    int parentHandle = parent.handle;
    int column = OS.gtk_tree_view_get_column(parentHandle, index);
    if (column == 0) {
      return new Rectangle(0, 0, 0, 0);
    }
    int list = OS.gtk_tree_view_column_get_cell_renderers(column);
    if (list == 0) {
      return new Rectangle(0, 0, 0, 0);
    }
    int count = OS.g_list_length(list);
    int pixbufRenderer = 0;
    int i = 0;
    while (i < count) {
      int renderer = OS.g_list_nth_data(list, i);
      if (OS.GTK_IS_CELL_RENDERER_PIXBUF(renderer)) {
        pixbufRenderer = renderer;
        break;
      }
      i++;
    }
    OS.g_list_free(list);
    if (pixbufRenderer == 0) {
      return new Rectangle(0, 0, 0, 0);
    }
    GdkRectangle rect = new GdkRectangle();
    int path = OS.gtk_tree_model_get_path(parent.modelHandle, handle);
    OS.gtk_widget_realize(parentHandle);
    OS.gtk_tree_view_get_cell_area(parentHandle, path, column, rect);
    OS.gtk_tree_path_free(path);
    int[] w = new int[1];
    if ((((OS.gtk_major_version() * 100) + (OS.gtk_minor_version() * 10)) + OS.gtk_micro_version())
        < 213) {
      OS.gtk_tree_view_column_cell_set_cell_data(column, parent.modelHandle, handle, false, false);
      OS.gtk_cell_renderer_get_size(pixbufRenderer, parentHandle, null, null, null, w, null);
    } else {
      int[] x = new int[1];
      OS.gtk_tree_view_column_cell_get_position(column, pixbufRenderer, x, w);
      rect.x += x[0];
    }
    return new Rectangle(rect.x, rect.y, w[0], rect.height);
  }
}
