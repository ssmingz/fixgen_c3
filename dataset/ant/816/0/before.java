class PlaceHold {
  public void testReferenceConversion() throws Exception {
    final DefaultConfiguration config = new DefaultConfiguration("test", "test");
    config.setAttribute("prop-a-ref", "id");
    final Integer refValue = new Integer(21);
    m_context.setProperty("id", refValue);
    registerConverter(ObjectToMyRole1Converter.class, Object.class, MyRole1.class);
    final ConfigTestReferenceConversion test = new ConfigTestReferenceConversion();
    configure(test, config);
    final ConfigTestReferenceConversion expected = new ConfigTestReferenceConversion();
    expected.setPropA(new MyRole1Adaptor(refValue));
    assertEquals(expected, test);
  }
}
