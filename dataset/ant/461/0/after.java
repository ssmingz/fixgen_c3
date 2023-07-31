class PlaceHold {
  protected void setUp() throws Exception {
    super.setUp();
    m_configurer = ((Configurer) (getServiceManager().lookup(ROLE)));
    m_context = new DefaultTaskContext();
    final File baseDir = new File(".").getAbsoluteFile();
    m_context.setProperty(BASE_DIRECTORY, baseDir);
  }
}
