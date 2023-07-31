class PlaceHold {
  public void test8() {
    executeTarget("test8");
    File f1 = new File("src/etc/testcases/taskdefs/test8.xml");
    if (!f1.exists()) {
      fail(
          "The fullpath attribute or the preserveLeadingSlashes attribute does not work propertly");
    }
  }
}
