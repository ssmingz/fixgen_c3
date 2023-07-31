class PlaceHold {
  public File[] getSelectedDirectories() {
    TreePath[] sels = _tree.getSelectionPaths();
    if (sels == null) {
      return new File[0];
    } else {
      Vector<File> v = new Vector<File>();
      for (TreePath tp : sels) {
        v.add(getFileForTreePath(tp));
      }
      return v.toArray(new File[0]);
    }
  }
}
