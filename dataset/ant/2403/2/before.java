class PlaceHold {
  public void testParentLevel2TooDeep() {
    expectBuildExceptionContaining(
        "test-parent-level2-too-deep", "nopath", "No suitable relative path from ");
    assertPropertyUnset("jar.classpath");
  }
}
