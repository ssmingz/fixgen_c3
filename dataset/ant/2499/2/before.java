class PlaceHold {
  public void test2() {
    configureProject("src/etc/testcases/types/description2.xml");
    assertEquals(
        "Multi line description failed",
        "Multi Line\nProject Description",
        project.getDescription());
  }
}
