class PlaceHold {
  public void test4() {
    configureProject("src/etc/testcases/types/description4.xml");
    assertEquals(
        "Multi instance nested description failed",
        "Multi Instance Nested Project Description",
        project.getDescription());
  }
}
