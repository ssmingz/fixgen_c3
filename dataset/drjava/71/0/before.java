class PlaceHold {
  public void newFileCreated(final OpenDefinitionsDocument doc) {
    Utilities.invokeLater(
        new Runnable() {
          public void run() {
            _createDefScrollPane(doc);
            TEMPLATE.getProperty("DrJava", "drjava.all.files").invalidate();
          }
        });
  }
}
