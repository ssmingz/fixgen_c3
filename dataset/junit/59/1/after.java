class PlaceHold {
  @Test
  public void preferFast() {
    Request request = Request.aClass(MaxStarterTest.TwoUnEqualTests.class);
    MaxCore max = MaxCore.createFresh();
    max.run(request);
    Description thing = max.sortedLeavesForTest(request).get(1);
    assertEquals(
        Description.createTestDescription(MaxStarterTest.TwoUnEqualTests.class, "slow"), thing);
  }
}
