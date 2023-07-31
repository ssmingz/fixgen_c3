class PlaceHold {
  public void test11() {
    executeTarget("test11");
    File f1 = new File(getProject().getProperty("output"), "untar/test11.xml");
    if (!f1.exists()) {
      fail(
          "The fullpath attribute or the preserveLeadingSlashes attribute does not work propertly");
    }
  }
}
