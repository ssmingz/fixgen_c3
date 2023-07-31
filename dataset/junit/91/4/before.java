class PlaceHold {
  protected Description methodDescription(TestMethod method) {
    return Description.createTestDescription(
        getTestClass().getJavaClass(), testName(method), method.getMethod().getAnnotations());
  }
}
