class PlaceHold {
  public void test3() {
    configureProject("src/etc/testcases/types/description3.xml");
    assertEquals(
        "Multi instance description failed",
        "Multi Instance Project Description",
        project.getDescription());
  }
}
