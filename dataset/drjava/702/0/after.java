class PlaceHold {
  public void setUp() throws Exception {
    super.setUp();
    DrJava.getConfig().resetToDefaults();
    _notifier = new GlobalEventNotifier();
    _doc = new DefinitionsDocument(_notifier);
    Utilities.invokeAndWait(
        new Runnable() {
          public void run() {
            DrJava.getConfig().setSetting(INDENT_LEVEL, indentLevel);
          }
        });
  }
}
