class PlaceHold {
  protected void setUp() throws Exception {
    super.setUp();
    m_builder = new DefaultProjectBuilder();
    m_builder.enableLogging(createLogger());
  }
}
