class PlaceHold {
  @Test
  public void testChildrenOfExcludedDirectory() {
    buildRule.getProject().executeTarget("children-of-excluded-dir-setup");
    DirectoryScanner ds = new DirectoryScanner();
    ds.setBasedir(new File(buildRule.getProject().getProperty("output")));
    ds.setExcludes(new String[] {"alpha/**"});
    ds.setFollowSymlinks(false);
    ds.scan();
    compareFiles(ds, new String[] {"delta/delta.xml"}, new String[] {"", "delta"});
    ds = new DirectoryScanner();
    ds.setBasedir(new File(buildRule.getProject().getProperty("output")));
    ds.setExcludes(new String[] {"alpha"});
    ds.setFollowSymlinks(false);
    ds.scan();
    compareFiles(
        ds,
        new String[] {"alpha/beta/beta.xml", "alpha/beta/gamma/gamma.xml", "delta/delta.xml"},
        new String[] {"", "alpha/beta", "alpha/beta/gamma", "delta"});
  }
}
