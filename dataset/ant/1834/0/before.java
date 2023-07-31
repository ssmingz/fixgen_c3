class PlaceHold {
  public void testNonExistentEntry() {
    ResourceLocation dtd = new ResourceLocation();
    dtd.setPublicId("PUBLIC ID ONE");
    dtd.setLocation("i/dont/exist.dtd");
    try {
      InputSource result = catalog.resolveEntity("PUBLIC ID ONE", "i/dont/exist.dtd");
      assertNull("Nonexistent Catalog entry should not be returned", result);
    } catch (Exception e) {
      fail("resolveEntity() failed!" + e.toString());
    }
    try {
      Source result = catalog.resolve("i/dont/exist.dtd", null);
      String expected = toURLString(new File(project.getBaseDir().toURL() + "/i/dont/exist.dtd"));
      String resultStr = new URL(((SAXSource) (result)).getInputSource().getSystemId()).getFile();
      assertTrue("Nonexistent Catalog entry return input", expected.endsWith(resultStr));
    } catch (Exception e) {
      fail("resolve() failed!" + e.toString());
    }
  }
}
