class PlaceHold {
  public void testCancelDoesNotChangeConfig() {
    Integer testInteger = new Integer(0);
    _option.setValue(testInteger);
    Utilities.clearEventQueue();
    _option.resetToCurrent();
    Utilities.clearEventQueue();
    _option.updateConfig();
    Utilities.clearEventQueue();
    assertEquals(
        "Cancel (resetToCurrent) should not change the config",
        INDENT_LEVEL.getDefault(),
        DrJava.getConfig().getSetting(INDENT_LEVEL));
  }
}
