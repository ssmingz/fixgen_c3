class PlaceHold {
  @Test
  public void testAbsolutePath() throws IOException, SAXException {
    ResourceLocation dtd = new ResourceLocation();
    dtd.setPublicId("-//stevo//DTD doc 1.0//EN");
    String sysid =
        (System.getProperty("root") + File.separator)
            + "src/etc/testcases/taskdefs/optional/xml/doc.dtd";
    dtd.setLocation(sysid);
    catalog.addDTD(dtd);
    File dtdFile = project.resolveFile(sysid);
    InputSource result =
        catalog.resolveEntity("-//stevo//DTD doc 1.0//EN", "nap:chemical+brothers");
    assertNotNull(result);
    assertEquals(toURLString(dtdFile), result.getSystemId());
  }
}
