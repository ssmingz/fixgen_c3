class PlaceHold {
  private void test8(String target) {
    executeTarget(target);
    File f1 = new File(getProject().getProperty("output"), "untar/test8.xml");
    if (!f1.exists()) {
      fail(
          "The fullpath attribute or the preserveLeadingSlashes attribute does not work propertly");
    }
  }
}
