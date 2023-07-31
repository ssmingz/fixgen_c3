class PlaceHold {
  public void setWidth(int width) {
    checkWidget();
    OS.gtk_tree_view_column_set_sizing(handle, GTK_TREE_VIEW_COLUMN_FIXED);
    OS.gtk_tree_view_column_set_fixed_width(handle, Math.max(1, width));
  }
}
