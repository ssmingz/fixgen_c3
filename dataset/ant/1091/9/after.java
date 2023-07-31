class PlaceHold {
  @Test
  public void testFullPathMatchesCaseSensitive() {
    DirectoryScanner ds = new DirectoryScanner();
    ds.setBasedir(new File(buildRule.getProject().getProperty("output")));
    ds.setIncludes(new String[] {"alpha/beta/gamma/GAMMA.XML"});
    ds.scan();
    compareFiles(ds, new String[] {}, new String[] {});
  }
}
