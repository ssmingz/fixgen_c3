class PlaceHold {
  protected void setUp() throws Exception {
    super.setUp();
    _option =
        new FileOptionComponent(
            OptionConstants.JAVAC_LOCATION,
            "Javac Location",
            new DefaultSwingFrame(),
            new JFileChooser());
    Utilities.clearEventQueue();
    DrJava.getConfig().resetToDefaults();
    Utilities.clearEventQueue();
  }
}
