class PlaceHold {
  public void testFullpathDiffersInCaseScanningInsensitive() {
    DirectoryScanner ds = new DirectoryScanner();
    ds.setBasedir(new File(getProject().getProperty("output")));
    ds.setIncludes(new String[] {"alpha/beta/gamma/gamma.xml", "alpha/beta/gamma/GAMMA.XML"});
    ds.setCaseSensitive(false);
    ds.scan();
    compareFiles(ds, new String[] {"alpha/beta/gamma/gamma.xml"}, new String[] {});
  }
}
