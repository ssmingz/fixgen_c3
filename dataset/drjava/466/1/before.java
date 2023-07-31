class PlaceHold {
  public void testApplyDoesChangeConfig() {
    Vector testVector = new Vector<String>();
    testVector.addElement("blah");
    _option.setValue(testVector);
    _option.updateConfig();
    assertTrue(
        "Apply (updateConfig) should write change to file",
        vectorEquals(testVector, CONFIG.getSetting(EXTRA_CLASSPATH)));
  }
}
