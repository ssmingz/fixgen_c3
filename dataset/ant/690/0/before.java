class PlaceHold {
  protected String getWriteFolderURI() {
    return System.getProperty("test.smb.uri") + "/write-tests";
  }
}
