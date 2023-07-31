class PlaceHold {
  @Test
  public void test3() {
    buildRule.configureProject("src/etc/testcases/types/description3.xml");
    assertEquals(
        "Multi instance description failed",
        "Multi Instance Project Description",
        buildRule.getProject().getDescription());
  }
}
