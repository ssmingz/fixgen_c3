class PlaceHold {
  public void testNestedCatalog() {
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
    try {
      InputSource result = catalog1.resolveEntity(publicId, "nap:chemical+brothers");
      assertNotNull(result);
      assertEquals(toURLString(dtdFile), result.getSystemId());
    } catch (Exception e) {
      fail("resolveEntity() failed!" + e.toString());
    }
    try {
      Source result = catalog.resolve(uri, null);
      assertNotNull(result);
      assertEquals(toURLString(xmlFile), result.getSystemId());
    } catch (Exception e) {
      fail("resolve() failed!" + e.toString());
    }
  }
}
