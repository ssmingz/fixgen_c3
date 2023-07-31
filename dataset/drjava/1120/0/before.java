class PlaceHold {
  public void launchCreateNewDirectory() {
    TreePath tp = _tree.getSelectionPath();
    if (tp != null) {
      launchCreateNewDirectory(tp);
    }
  }
}
