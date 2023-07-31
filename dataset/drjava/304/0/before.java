class PlaceHold {
  public void save(File f) {
    try {
      save(new FileOutputStream(f));
    } catch (FileNotFoundException e) {
      throw new XMLConfigException("Error in save", e);
    }
  }
}
