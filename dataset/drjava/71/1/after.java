class PlaceHold {
  public void fileOpened(final OpenDefinitionsDocument doc) {
    _fileOpened(doc);
    TEMPLATE.getProperty("DrJava", "drjava.all.files").invalidate();
  }
}
