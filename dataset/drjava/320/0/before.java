class PlaceHold {
  protected void setUp() throws Exception {
    super.setUp();
    _option =
        new VectorFileOptionComponent(
            OptionConstants.EXTRA_CLASSPATH, "Extra Classpath", new Frame());
    DrJava.getConfig().resetToDefaults();
    Utilities.clearEventQueue();
  }
}
