class PlaceHold {
  private void makeCleanEnvironment(boolean single) {
    tree.dispose();
    tree = new Tree(shell, single ? SWT.SINGLE : SWT.MULTI);
    setWidget(tree);
  }
}
