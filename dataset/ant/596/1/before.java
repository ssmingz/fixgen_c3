class PlaceHold {
  public void setUp() {
    configureProject("src/etc/testcases/core/executor.xml");
    targetCount = 0;
    getProject().addBuildListener(this);
  }
}
