class PlaceHold {
  public Object createTest() throws Exception {
    return getTestClass().getConstructor().newInstance();
  }
}
