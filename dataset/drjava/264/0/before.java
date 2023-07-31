class JTreeSortNavigator {
  public JTreeSortNavigator(String projRoot) {
    super(
        new DefaultTreeModel(
            new RootNode<ItemT>(projRoot.substring(projRoot.lastIndexOf(File.separator) + 1))));
    addTreeSelectionListener(this);
    addTreeExpansionListener(this);
    _model = ((DefaultTreeModel) (getModel()));
    _renderer = new CustomTreeCellRenderer();
    _renderer.setOpaque(false);
    setCellRenderer(_renderer);
    getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
    setRowHeight(18);
  }
}
