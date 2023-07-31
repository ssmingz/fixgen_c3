class PlaceHold {
  protected void setUp() throws Exception {
    super.setUp();
    _option =
        new VectorFileOptionComponent(
            OptionConstants.EXTRA_CLASSPATH, "Extra Classpath", new DefaultSwingFrame());
    DrJava.getConfig().resetToDefaults();
    Utilities.clearEventQueue();
  }
}
