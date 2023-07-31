class PlaceHold {
  public void testReferenceAttribute() throws Exception {
    final DefaultConfiguration config = new DefaultConfiguration("test", "test");
    config.setAttribute("some-prop-ref", "prop-a");
    final ConfigTestReferenceAttribute test = new ConfigTestReferenceAttribute();
    m_context.setProperty("prop-a", "some value");
    configure(test, config);
    final ConfigTestReferenceAttribute expected = new ConfigTestReferenceAttribute();
    expected.setSomeProp("some value");
    assertEquals(expected, test);
  }
}
