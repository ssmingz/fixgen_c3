class PlaceHold {
  public void dragLeave(DropTargetEvent event) {
    Tree tree = ((Tree) (control));
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
