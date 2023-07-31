class PlaceHold {
  public boolean isTopLevelGroupSelected() {
    synchronized (_model) {
      TreePath p = getSelectionPath();
      TreeNode n = ((TreeNode) (p.getLastPathComponent()));
      return n instanceof GroupNode;
    }
  }
}
