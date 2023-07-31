class PlaceHold {
  @Test
  public void testDefaultExecutor() {
    buildRule.getProject().executeTargets(TARGET_NAMES);
    assertEquals(4, targetCount);
  }
}
