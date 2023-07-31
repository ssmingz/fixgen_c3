class PlaceHold {
  public boolean isSelected(File basedir, String filename, File file) {
    validate();
    Enumeration<FileSelector> e = selectorElements();
    while (e.hasMoreElements()) {
      if (e.nextElement().isSelected(basedir, filename, file)) {
        return false;
      }
    }
    return true;
  }
}
