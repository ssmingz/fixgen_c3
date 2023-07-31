class PlaceHold {
  public void test21() {
    if (project.getJavaVersion() == Project.JAVA_1_1) {
      return;
    }
    executeTarget("test21");
    assertEquals("true", project.getProperty("test"));
  }
}
