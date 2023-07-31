class PlaceHold {
  public void testApplyThenResetDefault() {
    Integer testInteger = new Integer(10);
    _option.setValue(testInteger);
    Utilities.clearEventQueue();
    _option.updateConfig();
    Utilities.clearEventQueue();
    _option.resetToDefault();
    Utilities.clearEventQueue();
    _option.updateConfig();
    Utilities.clearEventQueue();
    assertEquals(
        "Apply (updateConfig) should write change to file",
        INDENT_LEVEL.getDefault(),
        DrJava.getConfig().getSetting(INDENT_LEVEL));
  }
}
