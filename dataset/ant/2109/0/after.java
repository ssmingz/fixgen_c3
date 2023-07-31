class PlaceHold {
  @Test
  public void testTaskErrorInIncluding() {
    buildRule.configureProject(
        "src/etc/testcases/core/include/including_file_task_error/build.xml");
    try {
      buildRule.executeTarget("test");
      fail("should have cause a build failure");
    } catch (BuildException e) {
      assertTrue(
          e.getMessage() + " should start with \'Warning: Could not find",
          e.getMessage().startsWith("Warning: Could not find file "));
      assertTrue(
          e.getLocation().toString() + " should end with build.xml:14: ",
          e.getLocation().toString().endsWith("build.xml:14: "));
    }
  }
}
