class PlaceHold {
  public void launchCreateNewDirectory() {
    if (!_treeIsGenerated) {
      return;
    }
    TreePath tp = _tree.getSelectionPath();
    if (tp != null) {
      launchCreateNewDirectory(tp);
    }
  }
}
