class PlaceHold {
  protected String getWriteFolderURI() {
    return System.getProperty("test.ftp.uri") + "/write-tests";
  }
}
