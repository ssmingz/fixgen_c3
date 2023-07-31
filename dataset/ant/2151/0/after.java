class PlaceHold {
  public void testLongLine() throws IOException, ManifestException {
    Project p = getProject();
    p.setUserProperty("test.longline", LONG_LINE);
    p.setUserProperty("test.long68name", LONG_68_NAME);
    p.setUserProperty("test.long70name", LONG_70_NAME);
    p.setUserProperty("test.notlongname", NOT_LONG_NAME);
    p.setUserProperty("test.value", VALUE);
    executeTarget("testLongLine");
    Manifest manifest = getManifest(EXPANDED_MANIFEST);
    Manifest.Section mainSection = manifest.getMainSection();
    String classpath = mainSection.getAttributeValue("class-path");
    assertEquals("Class-Path attribute was not set correctly - ", LONG_LINE, classpath);
    String value = mainSection.getAttributeValue(LONG_68_NAME);
    assertEquals("LONG_68_NAME_VALUE_MISMATCH", VALUE, value);
    value = mainSection.getAttributeValue(LONG_70_NAME);
    assertEquals("LONG_70_NAME_VALUE_MISMATCH", VALUE, value);
    value = mainSection.getAttributeValue(NOT_LONG_NAME);
    assertEquals("NOT_LONG_NAME_VALUE_MISMATCH", VALUE, value);
    BufferedReader in =
        new BufferedReader(new FileReader(new File(System.getProperty("root"), EXPANDED_MANIFEST)));
    Set set = new HashSet();
    String read = in.readLine();
    while (read != null) {
      set.add(read);
      read = in.readLine();
    }
    assertTrue("Manifest file should have contained string ", set.remove(" NOT_LONG"));
    assertTrue("Manifest file should have contained string ", set.remove(" NG"));
    assertTrue("Manifest file should have contained string ", set.remove(LONG_70_NAME + ": "));
    assertTrue(
        "Manifest file should have contained string ", set.remove(NOT_LONG_NAME + ": NOT_LO"));
  }
}
