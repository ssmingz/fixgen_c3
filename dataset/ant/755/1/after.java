class PlaceHold {
  @Test
  public void test1() {
    buildRule.configureProject("src/etc/testcases/types/description1.xml");
    assertEquals(
        "Single description failed",
        "Test Project Description",
        buildRule.getProject().getDescription());
  }
}
