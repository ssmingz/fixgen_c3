class PlaceHold {
  public void testNoLang() {
    expectBuildExceptionContaining(
        "nolang", "Absence of language attribute not detected", "requires a language attribute");
  }
}
