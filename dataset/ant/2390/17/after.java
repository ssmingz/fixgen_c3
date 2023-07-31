class PlaceHold {
  public void test5() {
    String baseDir = getProject().getProperty("basedir");
    try {
      String uri = FILE_UTILS.toURI(baseDir + "/property3.properties");
      getProject().setNewProperty("test5.url", uri);
    } catch (Exception ex) {
      throw new BuildException(ex);
    }
    expectLog("test5", "http.url is http://localhost:999");
  }
}
