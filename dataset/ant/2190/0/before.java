class PlaceHold {
  private void test8(String target) {
    executeTarget(target);
    File f1 = new File(System.getProperty("root"), "src/etc/testcases/taskdefs/test8.xml");
    if (!f1.exists()) {
      fail(
          "The fullpath attribute or the preserveLeadingSlashes attribute does not work propertly");
    }
  }
}
