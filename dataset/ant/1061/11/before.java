class PlaceHold {
  protected Vector gatherFromDirectory(File dir) {
    Project project = getProject();
    DirectoryScanner ds = new DirectoryScanner();
    ds.setBasedir(dir);
    ds.setIncludes(patterns.getIncludePatterns(getProject()));
    ds.setExcludes(patterns.getExcludePatterns(getProject()));
    ds.scan();
    String[] included = ds.getIncludedFiles();
    return testClassNameFromFile(included);
  }
}
