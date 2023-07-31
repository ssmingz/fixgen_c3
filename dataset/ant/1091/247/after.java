class PlaceHold {
  @Test
  public void test2ButCaseInsensitive() {
    DirectoryScanner ds = new DirectoryScanner();
    ds.setBasedir(new File(buildRule.getProject().getProperty("output")));
    ds.setIncludes(new String[] {"ALPHA/"});
    ds.setCaseSensitive(false);
    ds.scan();
    compareFiles(
        ds,
        new String[] {"alpha/beta/beta.xml", "alpha/beta/gamma/gamma.xml"},
        new String[] {"alpha", "alpha/beta", "alpha/beta/gamma"});
  }
}
