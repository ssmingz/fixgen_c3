class PlaceHold {
  protected FileObject getWriteFolder() throws Exception {
    final String uri = System.getProperty("test.ftp.uri") + "/write-tests";
    return m_manager.resolveFile(uri);
  }
}
