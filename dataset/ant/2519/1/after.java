class PlaceHold {
  @Test
  public void testTaskThatDoesntReallyExist() {
    buildRule.executeTarget("testTaskThatDoesntReallyExist");
    assertNull(buildRule.getProject().getProperty("testTaskThatDoesntReallyExist"));
  }
}
