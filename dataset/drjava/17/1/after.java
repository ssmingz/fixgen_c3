class PlaceHold {
  public void removeRegion(final R r) {
    assert EventQueue.isDispatchThread();
    _changeState.setLastAdded(null);
    DefaultMutableTreeNode regionNode = _regionToTreeNode.get(r);
    if (r == null) {
      throw new UnexpectedException(("Region node for region " + r) + " is null");
    }
    _regionManager.removeRegion(r);
    _regionToTreeNode.remove(r);
    DefaultMutableTreeNode parent = ((DefaultMutableTreeNode) (regionNode.getParent()));
    _regTreeModel.removeNodeFromParent(regionNode);
    if (parent.getChildCount() == 0) {
      OpenDefinitionsDocument doc = r.getDocument();
      _docToTreeNode.remove(doc);
      _regTreeModel.removeNodeFromParent(parent);
    }
    _changeState.updateButtons();
  }
}
