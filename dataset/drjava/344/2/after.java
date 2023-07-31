class PlaceHold {
  public void testApplyDoesChangeConfig() {
    Integer testInteger = Integer.valueOf(10);
    _option.setValue(testInteger);
    Utilities.clearEventQueue();
    _option.updateConfig();
    Utilities.clearEventQueue();
    assertEquals(
        "Apply (updateConfig) should write change to file",
        testInteger,
        DrJava.getConfig().getSetting(INDENT_LEVEL));
  }
}
