class PlaceHold {
  public void testBadNoProperty() {
    expectBuildExceptionContaining(
        "test-bad-no-property", "no-property", "Missing 'property' attribute!");
    assertPropertyUnset("jar.classpath");
  }
}
