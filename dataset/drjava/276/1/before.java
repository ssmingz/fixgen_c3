class PlaceHold {
  public void setActiveDoc(ItemT doc) {
    synchronized (_model) {
      DefaultMutableTreeNode node = _doc2node.get(doc);
      if (node == _current) {
        return;
      }
      if (this.contains(doc)) {
        TreeNode[] nodes = node.getPath();
        TreePath path = new TreePath(nodes);
        expandPath(path);
        setSelectionPath(path);
        scrollPathToVisible(path);
      }
    }
  }
}
