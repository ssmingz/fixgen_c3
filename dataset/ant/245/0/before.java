class PlaceHold {
  public void testDefaultExecutor() {
    getProject().executeTargets(TARGET_NAMES);
    assertEquals(4, targetCount);
  }
}
