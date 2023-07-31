class PlaceHold {
  public void testExcludeHasPrecedence() {
    DirectoryScanner ds = new DirectoryScanner();
    ds.setBasedir(new File(getProject().getProperty("output")));
    ds.setIncludes(new String[] {"alpha/**"});
    ds.setExcludes(new String[] {"alpha/**"});
    ds.scan();
    compareFiles(ds, new String[] {}, new String[] {});
  }
}
