class PlaceHold {
  public void setUp() {
    configureProject("src/etc/testcases/core/directoryscanner.xml");
    getProject().executeTarget("setUp");
  }
}
