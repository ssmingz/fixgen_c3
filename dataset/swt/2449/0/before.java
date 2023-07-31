class PlaceHold {
  public void dragLeave(DropTargetEvent event) {
    Table table = ((Table) (control));
    int handle = table.handle;
    OS.gtk_tree_view_unset_rows_drag_dest(handle);
    scrollBeginTime = 0;
    scrollIndex = -1;
  }
}
