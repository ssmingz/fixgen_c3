class PlaceHold {
  private void makeCleanEnvironment(boolean single) {
    if (tree != null) {
      tree.dispose();
    }
    tree = new Tree(shell, single ? SWT.SINGLE : SWT.MULTI);
    setWidget(tree);
  }
}
