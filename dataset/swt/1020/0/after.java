class PlaceHold {
  public void dragLeave(DropTargetEvent event) {
    Tree tree = ((Tree) (control));
    int handle = tree.handle;
    OS.gtk_tree_view_unset_rows_drag_dest(handle);
    scrollBeginTime = 0;
    scrollIndex = -1;
    expandBeginTime = 0;
    expandIndex = -1;
  }
}
