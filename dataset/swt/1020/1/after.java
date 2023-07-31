class PlaceHold {
  public void dragLeave(DropTargetEvent event) {
    Tree tree = ((Tree) (control));
    if (insertItem != null) {
      tree.setInsertMark(null, false);
      insertItem = null;
    }
    if (currentItem != null) {
      setDropSelection(tree, null);
      currentItem = null;
    }
    expandBeginTime = 0;
    expandItem = null;
    scrollBeginTime = 0;
    scrollItem = null;
  }
}
