class PlaceHold {
  public void setInsertMark(TreeItem item, boolean before) {
    checkWidget();
    if (item == null) {
      OS.gtk_tree_view_unset_rows_drag_dest(handle);
      return;
    }
    if (item.isDisposed()) {
      error(ERROR_INVALID_ARGUMENT);
    }
    if (item.parent != this) {
      return;
    }
    Rectangle rect = item.getBounds();
    int[] path = new int[1];
    OS.gtk_widget_realize(handle);
    if (!OS.gtk_tree_view_get_path_at_pos(handle, rect.x, rect.y, path, null, null, null)) {
      return;
    }
    if (path[0] == 0) {
      return;
    }
    int position = (before) ? OS.GTK_TREE_VIEW_DROP_BEFORE : OS.GTK_TREE_VIEW_DROP_AFTER;
    OS.gtk_tree_view_set_drag_dest_row(handle, path[0], position);
    OS.gtk_tree_path_free(path[0]);
  }
}
