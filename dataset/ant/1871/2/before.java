class PlaceHold {
  public boolean isSelected(File basedir, String filename, File file) {
    validate();
    Enumeration e = selectorElements();
    boolean result;
    while (e.hasMoreElements()) {
      result = ((FileSelector) (e.nextElement())).isSelected(basedir, filename, file);
      if (result) {
        return true;
      }
    }
    return false;
  }
}
