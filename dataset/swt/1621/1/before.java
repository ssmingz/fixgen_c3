class PlaceHold {
  void setScrollWidth(int column, int iter) {
    if (columnCount != 0) {
      return;
    }
    int width = OS.gtk_tree_view_column_get_width(column);
    int itemWidth = calculateWidth(column, iter);
    if (width < itemWidth) {
      OS.gtk_tree_view_column_set_fixed_width(column, itemWidth);
    }
  }
}
