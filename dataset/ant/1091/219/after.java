class PlaceHold {
  @Test
  public void testExcludeOneFile() {
    DirectoryScanner ds = new DirectoryScanner();
    ds.setBasedir(new File(buildRule.getProject().getProperty("output")));
    ds.setIncludes(new String[] {"**/*.xml"});
    ds.setExcludes(new String[] {"alpha/beta/b*xml"});
    ds.scan();
    compareFiles(ds, new String[] {"alpha/beta/gamma/gamma.xml"}, new String[] {});
  }
}
