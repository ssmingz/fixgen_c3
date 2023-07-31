class PlaceHold {
  public void testBadNoJarfile() {
    expectBuildExceptionContaining(
        "test-bad-no-jarfile", "no-jarfile", "Missing 'jarfile' attribute!");
    assertPropertyUnset("jar.classpath");
  }
}
