class PlaceHold {
  public void testApplyDoesChangeConfig() {
    Vector testVector = new Vector<File>();
    testVector.addElement(new File("blah"));
    _option.setValue(testVector);
    _option.updateConfig();
    assertTrue(
        "Apply (updateConfig) should write change to file",
        vectorEquals(testVector, CONFIG.getSetting(EXTRA_CLASSPATH)));
  }
}
