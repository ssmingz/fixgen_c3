class PlaceHold {
  public void testEmptyCatalog() {
    try {
      InputSource result = catalog.resolveEntity("PUBLIC ID ONE", "i/dont/exist.dtd");
      assertNull("Empty catalog should return null", result);
    } catch (Exception e) {
      fail("resolveEntity() failed!" + e.toString());
    }
    try {
      Source result = catalog.resolve("i/dont/exist.dtd", null);
      String expected = toURLString(new File(project.getBaseDir() + "/i/dont/exist.dtd"));
      String resultStr = new URL(((SAXSource) (result)).getInputSource().getSystemId()).getFile();
      assertTrue("Empty catalog should return input", expected.endsWith(resultStr));
    } catch (Exception e) {
      fail("resolve() failed!" + e.toString());
    }
  }
}
