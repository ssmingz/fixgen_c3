class PlaceHold {
  public void testImplicitExplicit() {
    expectSpecificBuildException(
        "implicit.explicit",
        "Only one element allowed when using implicit elements",
        "Only one element allowed when using implicit elements");
  }
}
