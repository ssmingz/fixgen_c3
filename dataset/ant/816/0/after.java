class PlaceHold {
  public void testReferenceConversion() throws Exception {
    final DefaultConfiguration config = new DefaultConfiguration("test", "test");
    config.setAttribute("prop-a-ref", "id");
    final Integer refValue = new Integer(21);
    m_context.setProperty("id", refValue);
    registerConverter(ObjectToMyRole1Converter.class, Object.class, MyRole1.class);
    final ConfigTestReferenceConversion test = new ConfigTestReferenceConversion();
    try {
      configure(test, config);
    } catch (ConfigurationException e) {
      return;
    }
    fail("-ref pattern on attributes no longer supported");
  }
}
