class PlaceHold {
  public void testBadDirectory() {
    expectBuildExceptionContaining(
        "test-bad-directory", "bad-jar-dir", "Jar's directory not found:");
    assertPropertyUnset("jar.classpath");
  }
}
