class PlaceHold {
  public Rectangle getBounds() {
    checkWidget();
    int parentHandle = parent.handle;
    GdkRectangle rect = new GdkRectangle();
    int column = OS.gtk_tree_view_get_column(parentHandle, 0);
    int path = OS.gtk_tree_model_get_path(parent.modelHandle, handle);
    OS.gtk_widget_realize(parentHandle);
    OS.gtk_tree_view_get_cell_area(parentHandle, path, column, rect);
    boolean isExpander = OS.gtk_tree_model_iter_n_children(parent.modelHandle, handle) > 0;
    boolean isExpanded = OS.gtk_tree_view_row_expanded(parentHandle, path);
    OS.gtk_tree_view_column_cell_set_cell_data(
        column, parent.modelHandle, handle, isExpander, isExpanded);
    int[] x = new int[1];
    int[] width = new int[1];
    OS.gtk_cell_renderer_get_size(parent.textRenderer, parentHandle, null, null, null, width, null);
    rect.width = width[0];
    int[] buffer = new int[1];
    if (OS.gtk_tree_view_get_expander_column(parentHandle) == column) {
      OS.gtk_widget_style_get(parentHandle, expander_size, buffer, 0);
      rect.x += buffer[0] + TreeItem.EXPANDER_EXTRA_PADDING;
    }
    OS.gtk_widget_style_get(parentHandle, horizontal_separator, buffer, 0);
    int horizontalSeparator = buffer[0];
    rect.x += horizontalSeparator;
    if (OS.GTK_VERSION >= OS.VERSION(2, 1, 3)) {
      OS.gtk_tree_view_column_cell_get_position(column, parent.textRenderer, x, null);
      rect.x += x[0];
    } else {
      if ((parent.style & SWT.CHECK) != 0) {
        OS.gtk_cell_renderer_get_size(
            parent.checkRenderer, parentHandle, null, null, null, width, null);
        rect.x += width[0] + horizontalSeparator;
      }
      OS.gtk_cell_renderer_get_size(
          parent.pixbufRenderer, parentHandle, null, null, null, width, null);
      rect.x += width[0] + horizontalSeparator;
    }
    int border = parent.getBorderWidth();
    rect.x += border;
    rect.y += border;
    OS.gtk_tree_path_free(path);
    int[] wx = new int[1];
    OS.gtk_tree_view_tree_to_widget_coords(parentHandle, rect.x, 0, wx, null);
    rect.x = wx[0];
    return new Rectangle(rect.x, rect.y, rect.width, rect.height);
  }
}
