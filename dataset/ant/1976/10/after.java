class PlaceHold {
  @Test
  public void testTaskThatIsntDefined() {
    buildRule.executeTarget("testTaskThatIsntDefined");
    assertNull(buildRule.getProject().getProperty("testTaskThatIsntDefined"));
  }
}
