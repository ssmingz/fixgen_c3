class PlaceHold {
  public void testCancelDoesNotChangeConfig() {
    Vector testVector = new Vector<String>();
    testVector.addElement("test");
    _option.setValue(testVector);
    _option.resetToCurrent();
    _option.updateConfig();
    assertTrue(
        "Cancel (resetToCurrent) should not change the config",
        vectorEquals(EXTRA_CLASSPATH.getDefault(), CONFIG.getSetting(EXTRA_CLASSPATH)));
  }
}
