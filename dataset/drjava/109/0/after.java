class JTreeNavigator {
  public JTreeNavigator(String name) {
    super(new DefaultTreeModel(new DefaultMutableTreeNode(name)));
    this.addTreeSelectionListener(this);
    _model = ((DefaultTreeModel) (this.getModel()));
    _root = ((DefaultMutableTreeNode) (_model.getRoot()));
    _renderer = new DefaultTreeCellRenderer();
    _renderer.setOpaque(true);
    this.setCellRenderer(_renderer);
  }
}
