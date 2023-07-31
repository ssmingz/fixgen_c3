class PlaceHold {
  public void deselectAll() {
    checkWidget();
    blockSignal(handle, Selection);
    int selection = OS.gtk_tree_view_get_selection(handle);
    OS.gtk_tree_selection_unselect_all(selection);
    unblockSignal(handle, Selection);
  }
}
