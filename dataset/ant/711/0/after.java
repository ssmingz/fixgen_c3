class PlaceHold {
  public void testReferenceAttribute() throws Exception {
    final DefaultConfiguration config = new DefaultConfiguration("test", "test");
    config.setAttribute("some-prop-ref", "prop-a");
    final ConfigTestReferenceAttribute test = new ConfigTestReferenceAttribute();
    m_context.setProperty("prop-a", "some value");
    try {
      configure(test, config);
    } catch (ConfigurationException e) {
      return;
    }
    fail("-ref pattern on attributes no longer supported");
  }
}
