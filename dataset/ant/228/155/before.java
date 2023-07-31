class PlaceHold {
  public synchronized void appendSelector(FileSelector selector) {
    if (isReference()) {
      throw noChildrenAllowed();
    }
    selectors.addElement(selector);
    directoryScanner = null;
    setChecked(false);
  }
}
