class PlaceHold {
  public int getItemHeight() {
    checkWidget();
    if (OS.gtk_tree_model_iter_n_children(modelHandle, 0) == 0) {
      return 18;
    }
    GdkRectangle rect = new GdkRectangle();
    int path = OS.gtk_tree_path_new_from_string(Converter.wcsToMbcs(null, "0", true));
    int column = OS.gtk_tree_view_get_column(handle, 0);
    OS.gtk_tree_view_get_cell_area(handle, path, column, rect);
    OS.gtk_tree_path_free(path);
    return rect.height;
  }
}
