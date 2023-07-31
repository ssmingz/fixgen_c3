class PlaceHold {
  public void dragLeave(DropTargetEvent event) {
    Table table = ((Table) (control));
    int handle = table.handle;
    OS.gtk_tree_view_set_drag_dest_row(handle, 0, GTK_TREE_VIEW_DROP_BEFORE);
    scrollBeginTime = 0;
    scrollIndex = -1;
  }
}
