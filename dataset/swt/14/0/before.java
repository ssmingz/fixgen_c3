class PlaceHold {
  public Rectangle getBounds(int index) {
    checkWidget();
    int parentHandle = parent.handle;
    int column = OS.gtk_tree_view_get_column(parentHandle, index);
    if (column == 0) {
      return new Rectangle(0, 0, 0, 0);
    }
    GdkRectangle rect = new GdkRectangle();
    int path = OS.gtk_tree_model_get_path(parent.modelHandle, handle);
    OS.gtk_widget_realize(parentHandle);
    OS.gtk_tree_view_get_cell_area(parentHandle, path, column, rect);
    OS.gtk_tree_path_free(path);
    int[] wx = new int[1];
    OS.gtk_tree_view_tree_to_widget_coords(parentHandle, rect.x, 0, wx, null);
    rect.x = wx[0];
    if ((index == 0) && ((parent.style & SWT.CHECK) != 0)) {
      if ((((OS.gtk_major_version() * 100) + (OS.gtk_minor_version() * 10))
              + OS.gtk_micro_version())
          >= 213) {
        int[] x = new int[1];
        int[] w = new int[1];
        OS.gtk_tree_view_column_cell_get_position(column, parent.checkRenderer, x, w);
        rect.x += x[0] + w[0];
        rect.width -= x[0] + w[0];
      }
    }
    int border = parent.getBorderWidth();
    int headerHeight = parent.getHeaderHeight();
    return new Rectangle(rect.x + border, rect.y + headerHeight, rect.width + 1, rect.height + 1);
  }
}
