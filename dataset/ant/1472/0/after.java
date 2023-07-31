class PlaceHold {
  protected FileObject getWriteFolder() throws Exception {
    final String uri = System.getProperty("test.smb.uri") + "/write-tests";
    return m_manager.resolveFile(uri);
  }
}
