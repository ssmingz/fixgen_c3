class PlaceHold {
  public void test6() {
    executeTarget("test6");
    assertEquals("scott", project.getProperty("db.user"));
  }
}
