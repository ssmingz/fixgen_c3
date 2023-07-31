class PlaceHold {
  @Test
  public void testSimpleCompile() {
    buildRule.executeTarget("simple-compile");
    assertTrue(
        new File(buildRule.getProject().getProperty("output"), "org_example_Foo.h").exists());
  }
}
