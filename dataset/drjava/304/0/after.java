class PlaceHold {
  public void save(File f) {
    if (isDelegated()) {
      _parent.save(f);
      return;
    }
    try {
      save(new FileOutputStream(f));
    } catch (FileNotFoundException e) {
      throw new XMLConfigException("Error in save", e);
    }
  }
}
