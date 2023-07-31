class PlaceHold {
  public synchronized void appendSelector(FileSelector selector) {
    if (isReference()) {
      throw noChildrenAllowed();
    }
    selectors.add(selector);
    directoryScanner = null;
    setChecked(false);
  }
}
