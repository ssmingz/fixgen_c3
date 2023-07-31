class PlaceHold {
  public void newFileCreated(final OpenDefinitionsDocument doc) {
    _createDefScrollPane(doc);
    TEMPLATE.getProperty("DrJava", "drjava.all.files").invalidate();
  }
}
