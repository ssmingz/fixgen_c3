class PlaceHold {
  public void testBadNoClassPath() {
    expectBuildExceptionContaining(
        "test-bad-no-classpath", "no-classpath", "Missing nested <classpath>!");
    assertPropertyUnset("jar.classpath");
  }
}
