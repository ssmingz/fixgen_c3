class PlaceHold {
  public void testNoName() {
    expectBuildExceptionContaining(
        "noname", "Absence of name attribute not detected", "scriptdef requires a name attribute");
  }
}
