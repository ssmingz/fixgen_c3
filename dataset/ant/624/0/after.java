class PlaceHold {
  @Test
  public void testEntryReference() throws IOException, SAXException, TransformerException {
    String publicId = "-//stevo//DTD doc 1.0//EN";
    String sysid = "src/etc/testcases/taskdefs/optional/xml/doc.dtd";
    ResourceLocation dtd = new ResourceLocation();
    dtd.setPublicId(publicId);
    dtd.setLocation(sysid);
    catalog.addDTD(dtd);
    File dtdFile = project.resolveFile(sysid);
    String uri = "http://foo.com/bar/blah.xml";
    String uriLoc = "src/etc/testcases/taskdefs/optional/xml/about.xml";
    ResourceLocation entity = new ResourceLocation();
    entity.setPublicId(uri);
    entity.setLocation(uriLoc);
    catalog.addEntity(entity);
    File xmlFile = project.resolveFile(uriLoc);
    project.addReference("catalog", catalog);
    XMLCatalog catalog1 = newCatalog();
    project.addReference("catalog1", catalog1);
    XMLCatalog catalog2 = newCatalog();
    project.addReference("catalog2", catalog1);
    catalog1.setRefid(new Reference(project, "catalog"));
    catalog2.setRefid(new Reference(project, "catalog1"));
    InputSource isResult = catalog2.resolveEntity(publicId, "nap:chemical+brothers");
    assertNotNull(isResult);
    assertEquals(toURLString(dtdFile), isResult.getSystemId());
    Source result = catalog.resolve(uri, null);
    assertNotNull(result);
    assertEquals(toURLString(xmlFile), result.getSystemId());
  }
}
