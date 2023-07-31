class PlaceHold {
  public int getSelectionCount() {
    checkWidget();
    selSize = 0;
    selIndices = new int[items.length];
    int selection = OS.gtk_tree_view_get_selection(handle);
    OS.gtk_tree_selection_selected_foreach(selection, getDisplay().selectionIterProc, handle);
    return selSize;
  }
}
