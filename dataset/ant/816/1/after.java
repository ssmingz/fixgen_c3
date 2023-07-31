class PlaceHold {
  public void testIdResolve() throws Exception {
    final DefaultConfiguration config = new DefaultConfiguration("test", "test");
    config.setAttribute("some-prop-ref", "${id}");
    final ConfigTestIdResolve test = new ConfigTestIdResolve();
    m_context.setProperty("id", "prop-a");
    m_context.setProperty("prop-a", "some indirect value");
    try {
      configure(test, config);
    } catch (ConfigurationException e) {
      return;
    }
    fail("-ref pattern on attributes no longer supported");
  }
}
