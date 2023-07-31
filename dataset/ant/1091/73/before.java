class PlaceHold {
  public void test1() {
    DirectoryScanner ds = new DirectoryScanner();
    ds.setBasedir(new File(getProject().getProperty("output")));
    ds.setIncludes(new String[] {"alpha"});
    ds.scan();
    compareFiles(ds, new String[] {}, new String[] {"alpha"});
  }
}
