class PlaceHold {
  public void testEncoding() {
    expectSpecificBuildException(
        "encoding",
        "<untar> overrides setEncoding.",
        "The untar task doesn't support the " + "encoding attribute");
  }
}
