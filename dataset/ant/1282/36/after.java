class PlaceHold {
  @Test
  public void test2() {
    buildRule.configureProject("src/etc/testcases/types/description2.xml");
    assertEquals(
        "Multi line description failed",
        "Multi Line\nProject Description",
        buildRule.getProject().getDescription());
  }
}
