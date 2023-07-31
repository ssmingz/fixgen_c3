class PlaceHold {
  public void testParentDiffersInCaseScanningInsensitive() {
    DirectoryScanner ds = new DirectoryScanner();
    ds.setBasedir(new File(getProject().getProperty("output")));
    ds.setIncludes(new String[] {"alpha/", "ALPHA/beta/"});
    ds.setCaseSensitive(false);
    ds.scan();
    compareFiles(
        ds,
        new String[] {"alpha/beta/beta.xml", "alpha/beta/gamma/gamma.xml"},
        new String[] {"alpha", "alpha/beta", "alpha/beta/gamma"});
  }
}
