class PlaceHold {
  public void testCustomClasses() {
    BFT bft = new BFT();
    bft.setUp();
    try {
      bft.doTarget("modifiedselectortest-customClasses");
      String fsFullValue = bft.getProperty("fs.full.value");
      String fsModValue = bft.getProperty("fs.mod.value");
      assertNotNull("'fs.full.value' must be set.", fsFullValue);
      assertTrue("'fs.full.value' must not be null.", !"".equals(fsFullValue));
      assertTrue("'fs.full.value' must contain ant.bat.", fsFullValue.indexOf("ant.bat") > (-1));
      assertNotNull("'fs.mod.value' must be set.", fsModValue);
      assertTrue("'fs.mod.value' must be empty.", "".equals(fsModValue));
    } finally {
      bft.doTarget("modifiedselectortest-scenario-clean");
      bft.deletePropertiesfile();
      bft.tearDown();
    }
  }
}
