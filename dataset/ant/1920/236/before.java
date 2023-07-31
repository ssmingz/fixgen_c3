class PlaceHold {
  public void testFullPathMatchesCaseInsensitive() {
    DirectoryScanner ds = new DirectoryScanner();
    ds.setCaseSensitive(false);
    ds.setBasedir(new File(getProject().getProperty("output")));
    ds.setIncludes(new String[] {"alpha/beta/gamma/GAMMA.XML"});
    ds.scan();
    compareFiles(ds, new String[] {"alpha/beta/gamma/gamma.xml"}, new String[] {});
  }
}
