class PlaceHold {
  public void test10() {
    executeTarget("test10");
    File f1 = new File(getProject().getProperty("output"), "untar/test10.xml");
    if (!f1.exists()) {
      fail(
          "The fullpath attribute or the preserveLeadingSlashes attribute does not work propertly");
    }
  }
}
