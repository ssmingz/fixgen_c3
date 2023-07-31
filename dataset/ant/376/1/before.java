class PlaceHold {
  public void testSimpleCompile() {
    executeTarget("simple-compile");
    assertTrue(new File(getProject().getProperty("output"), "org_example_Foo.h").exists());
  }
}
