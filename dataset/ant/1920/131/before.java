class PlaceHold {
  public void testAlternateExcludeInclude() {
    DirectoryScanner ds = new DirectoryScanner();
    ds.setBasedir(new File(getProject().getProperty("output")));
    ds.setExcludes(new String[] {"alpha/**", "alpha/beta/gamma/**"});
    ds.setIncludes(new String[] {"alpha/beta/**"});
    ds.scan();
    compareFiles(ds, new String[] {}, new String[] {});
  }
}
