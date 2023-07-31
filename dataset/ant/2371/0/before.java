class PlaceHold {
  public void testIdResolve() throws Exception {
    final DefaultConfiguration config = new DefaultConfiguration("test", "test");
    config.setAttribute("some-prop-ref", "${id}");
    final ConfigTestIdResolve test = new ConfigTestIdResolve();
    m_context.setProperty("id", "prop-a");
    m_context.setProperty("prop-a", "some indirect value");
    configure(test, config);
    final ConfigTestIdResolve expected = new ConfigTestIdResolve();
    expected.setSomeProp("some indirect value");
    assertEquals(expected, test);
  }
}
