class PlaceHold {
  public void setUp() throws Exception {
    super.setUp();
    DrJava.getConfig().resetToDefaults();
    _notifier = new GlobalEventNotifier();
    _doc = new DefinitionsDocument(_notifier);
    DrJava.getConfig().setSetting(INDENT_LEVEL, indentLevel);
  }
}
