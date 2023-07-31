class PlaceHold {
  @Test
  public void testOrder1() throws IOException, ManifestException {
    buildRule.executeTarget("testOrder1");
    Manifest manifest = getManifest(EXPANDED_MANIFEST);
    Enumeration e = manifest.getSectionNames();
    String section1 = ((String) (e.nextElement()));
    String section2 = ((String) (e.nextElement()));
    assertEquals("First section name unexpected", "Test1", section1);
    assertEquals("Second section name unexpected", "Test2", section2);
    Manifest.Section section = manifest.getSection("Test1");
    e = section.getAttributeKeys();
    String attr1Key = ((String) (e.nextElement()));
    String attr2Key = ((String) (e.nextElement()));
    String attr1 = section.getAttribute(attr1Key).getName();
    String attr2 = section.getAttribute(attr2Key).getName();
    assertEquals("First attribute name unexpected", "TestAttr1", attr1);
    assertEquals("Second attribute name unexpected", "TestAttr2", attr2);
  }
}
