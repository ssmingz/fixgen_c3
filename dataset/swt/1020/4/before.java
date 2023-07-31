class PlaceHold {
  public void dragLeave(DropTargetEvent event) {
    if (!checkWidget(event)) {
      return;
    }
    Tree tree = ((Tree) (((DropTarget) (event.widget)).getControl()));
    if (insertItem != null) {
      setInsertMark(tree, null, false);
      insertItem = null;
    }
    expandBeginTime = 0;
    expandItem = null;
    scrollBeginTime = 0;
    scrollItem = null;
  }
}
