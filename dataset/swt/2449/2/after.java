class PlaceHold {
  public void dragLeave(DropTargetEvent event) {
    Tree tree = ((Tree) (control));
    int handle = tree.handle;
    OS.gtk_tree_view_set_drag_dest_row(handle, 0, GTK_TREE_VIEW_DROP_BEFORE);
    scrollBeginTime = 0;
    scrollIndex = -1;
    expandBeginTime = 0;
    expandIndex = -1;
  }
}
