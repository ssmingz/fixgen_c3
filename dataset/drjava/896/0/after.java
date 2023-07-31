class PlaceHold {
  public boolean isGroupSelected() {
    synchronized (_model) {
      TreePath p = getSelectionPath();
      TreeNode n = ((TreeNode) (p.getLastPathComponent()));
      return n instanceof InnerNode;
    }
  }
}
