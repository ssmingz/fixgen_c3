class PlaceHold {
  private void _setupRegionTree() {
    _regionRootNode = new DefaultMutableTreeNode(_title);
    _regTreeModel = new DefaultTreeModel(_regionRootNode);
    _regTree = new RegionTree(_regTreeModel);
    _regTree.setEditable(false);
    _regTree.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
    _regTree.setShowsRootHandles(true);
    _regTree.setRootVisible(false);
    _regTree.putClientProperty("JTree.lineStyle", "Angled");
    _regTree.setScrollsOnExpand(true);
    _regTree.addTreeSelectionListener(
        new TreeSelectionListener() {
          public void valueChanged(TreeSelectionEvent e) {
            updateButtons();
          }
        });
    dtcr = new RegionRenderer();
    dtcr.setOpaque(false);
    _setColors(dtcr);
    _regTree.setCellRenderer(dtcr);
    _leftPane.add(new JScrollPane(_regTree));
    _initPopup();
    ToolTipManager.sharedInstance().registerComponent(_regTree);
  }
}
