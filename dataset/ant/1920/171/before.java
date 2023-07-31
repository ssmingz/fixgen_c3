class PlaceHold {
  public void testAlternateIncludeExclude() {
    DirectoryScanner ds = new DirectoryScanner();
    ds.setBasedir(new File(getProject().getProperty("output")));
    ds.setIncludes(new String[] {"alpha/**", "alpha/beta/gamma/**"});
    ds.setExcludes(new String[] {"alpha/beta/**"});
    ds.scan();
    compareFiles(ds, new String[] {}, new String[] {"alpha"});
  }
}
