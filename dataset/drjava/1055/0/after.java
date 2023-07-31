class PlaceHold {
  protected void ensureHasChildren(DefaultMutableTreeNode node) {
    if (!_treeIsGenerated) {
      return;
    }
    if ((node.getChildCount() == 1) && (node.getChildAt(0) instanceof EmptyTreeNode)) {
      hourglassOn();
      File parentFile = getFileForTreeNode(node);
      node.removeAllChildren();
      File[] childFiles = parentFile.listFiles();
      if (childFiles != null) {
        Arrays.sort(childFiles, _fileComparator);
        for (File f : childFiles) {
          if ((f.isDirectory() || (_showFiles && allowFile(f)))
              && (_showHidden || (!f.isHidden()))) {
            DefaultMutableTreeNode n = makeFileNode(f);
            node.add(n);
            ((DefaultTreeModel) (_tree.getModel())).nodeStructureChanged(node);
          }
        }
      }
      ((DefaultTreeModel) (_tree.getModel())).nodeStructureChanged(node);
      hourglassOff();
    }
  }
}
