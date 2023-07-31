class PlaceHold {
  @Test
  public void test4() {
    buildRule.configureProject("src/etc/testcases/types/description4.xml");
    assertEquals(
        "Multi instance nested description failed",
        "Multi Instance Nested Project Description",
        buildRule.getProject().getDescription());
  }
}
