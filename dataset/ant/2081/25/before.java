class PlaceHold {
  public void test1() {
    configureProject("src/etc/testcases/types/description1.xml");
    assertEquals("Single description failed", "Test Project Description", project.getDescription());
  }
}
