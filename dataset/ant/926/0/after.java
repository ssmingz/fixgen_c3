class PlaceHold {
  @Test
  public void test5() {
    try {
      buildRule.executeTarget("test5");
      fail("BuildException expected: No public execute() in " + Project.class);
    } catch (BuildException ex) {
    }
  }
}
