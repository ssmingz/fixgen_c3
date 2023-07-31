class PlaceHold {
  @Test
  public void testNestedCatalog() throws IOException, SAXException, TransformerException {
    String publicId = "-//stevo//DTD doc 1.0//EN";
    String dtdLoc = "src/etc/testcases/taskdefs/optional/xml/doc.dtd";
    ResourceLocation dtd = new ResourceLocation();
    dtd.setPublicId(publicId);
    dtd.setLocation(dtdLoc);
    catalog.addDTD(dtd);
    File dtdFile = project.resolveFile(dtdLoc);
    String uri = "http://foo.com/bar/blah.xml";
    String uriLoc = "src/etc/testcases/taskdefs/optional/xml/about.xml";
    ResourceLocation entity = new ResourceLocation();
    entity.setPublicId(uri);
    entity.setLocation(uriLoc);
    catalog.addEntity(entity);
    File xmlFile = project.resolveFile(uriLoc);
    XMLCatalog catalog1 = newCatalog();
    catalog1.addConfiguredXMLCatalog(catalog);
    InputSource isResult = catalog1.resolveEntity(publicId, "nap:chemical+brothers");
    assertNotNull(isResult);
    assertEquals(toURLString(dtdFile), isResult.getSystemId());
    Source result = catalog.resolve(uri, null);
    assertNotNull(result);
    assertEquals(toURLString(xmlFile), result.getSystemId());
  }
}
