class PlaceHold {
  protected TestRunner createRunner(Class clazz) {
    return new TestRunner(new JUnitTest(clazz.getName()), true, true);
  }
}
