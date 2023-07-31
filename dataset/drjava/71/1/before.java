class PlaceHold {
  public void fileOpened(final OpenDefinitionsDocument doc) {
    Utilities.invokeLater(
        new Runnable() {
          public void run() {
            _fileOpened(doc);
            TEMPLATE.getProperty("DrJava", "drjava.all.files").invalidate();
          }
        });
  }
}
