class PlaceHold {
  @Test
  public void test10() {
    buildRule.executeTarget("test10");
    File f1 = new File(buildRule.getProject().getProperty("output"), "untar/test10.xml");
    if (!f1.exists()) {
      fail(
          "The fullpath attribute or the preserveLeadingSlashes attribute does not work propertly");
    }
  }
}
