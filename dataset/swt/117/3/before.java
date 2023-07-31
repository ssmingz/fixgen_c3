class PlaceHold {
  public void setWidth(int width) {
    checkWidget();
    if (width > 0) {
      useFixedWidth = true;
      OS.gtk_tree_view_column_set_fixed_width(handle, width);
      OS.gtk_tree_view_column_set_visible(handle, true);
    } else {
      OS.gtk_tree_view_column_set_visible(handle, false);
    }
  }
}
