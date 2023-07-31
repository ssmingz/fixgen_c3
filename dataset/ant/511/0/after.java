class PlaceHold {
  public void test21() {
    if (JavaEnvUtils.isJavaVersion(JAVA_1_1)) {
      return;
    }
    executeTarget("test21");
    assertEquals("true", project.getProperty("test"));
  }
}
