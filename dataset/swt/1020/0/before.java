class PlaceHold {
  public void dragLeave(DropTargetEvent event) {
    if (!checkWidget(event)) {
      return;
    }
    Tree tree = ((Tree) (((DropTarget) (event.widget)).getControl()));
    int handle = tree.handle;
    OS.gtk_tree_view_unset_rows_drag_dest(handle);
    scrollBeginTime = 0;
    scrollIndex = -1;
    expandBeginTime = 0;
    expandIndex = -1;
  }
}
