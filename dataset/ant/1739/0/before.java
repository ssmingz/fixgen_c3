class PlaceHold {
  public void test9() {
    expectBuildExceptionContaining(
        "test9",
        "Construction is invalid - Name attribute should not be used",
        "Specify the section name using the \"name\" attribute of the <section> element");
  }
}
