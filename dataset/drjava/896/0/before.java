class PlaceHold {
  public synchronized boolean isGroupSelected() {
    TreePath p = getSelectionPath();
    TreeNode n = ((TreeNode) (p.getLastPathComponent()));
    return n instanceof InnerNode;
  }
}
