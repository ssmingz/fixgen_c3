class PlaceHold {
  public synchronized boolean isTopLevelGroupSelected() {
    TreePath p = getSelectionPath();
    TreeNode n = ((TreeNode) (p.getLastPathComponent()));
    return n instanceof GroupNode;
  }
}
