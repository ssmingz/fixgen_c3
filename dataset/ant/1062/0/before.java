class PlaceHold {
  public void testJarAndClassName() {
    expectBuildException("testJarAndClassName", "Should not be able to set both classname AND jar");
  }
}
