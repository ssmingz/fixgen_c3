class PlaceHold {
  protected void setUp() throws Exception {
    m_manager = new DefaultFileSystemManager();
    m_baseFolder = m_manager.resolveFile(getBaseFolderURI());
    final String eol = System.getProperty("line.separator");
    m_charContent = (("This is a test file." + eol) + "With 2 lines in it.") + eol;
  }
}
