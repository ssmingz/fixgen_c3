class PlaceHold {
  public void setUp() {
    if (supportsSymlinks) {
      configureProject("src/etc/testcases/taskdefs/optional/unix/symlink.xml");
      executeTarget("setUp");
    }
  }
}
