class PlaceHold {
  public void test3() {
    DirectoryScanner ds = new DirectoryScanner();
    ds.setBasedir(new File(getProject().getProperty("output")));
    ds.scan();
    compareFiles(
        ds,
        new String[] {"alpha/beta/beta.xml", "alpha/beta/gamma/gamma.xml"},
        new String[] {"", "alpha", "alpha/beta", "alpha/beta/gamma"});
  }
}
