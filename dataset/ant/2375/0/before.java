class PlaceHold {
  public void test2() {
    expectBuildException(
        "test2", "You must not specify more than one " + "attribute when using refid");
  }
}
