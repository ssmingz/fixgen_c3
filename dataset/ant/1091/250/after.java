class PlaceHold {
  @Test
  public void testFullPathMatchesCaseInsensitive() {
    DirectoryScanner ds = new DirectoryScanner();
    ds.setCaseSensitive(false);
    ds.setBasedir(new File(buildRule.getProject().getProperty("output")));
    ds.setIncludes(new String[] {"alpha/beta/gamma/GAMMA.XML"});
    ds.scan();
    compareFiles(ds, new String[] {"alpha/beta/gamma/gamma.xml"}, new String[] {});
  }
}
