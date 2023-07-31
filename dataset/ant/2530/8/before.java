class PlaceHold {
  public void setupDirectoryScanner(FileScanner ds, Project p) throws TaskException {
    if (ds == null) {
      throw new IllegalArgumentException("ds cannot be null");
    }
    ds.setBasedir(dir);
    for (int i = 0; i < additionalPatterns.size(); i++) {
      Object o = additionalPatterns.elementAt(i);
      defaultPatterns.append(((PatternSet) (o)), p);
    }
    p.log(
        (("FileSet: Setup file scanner in dir " + dir) + " with ") + defaultPatterns, p.MSG_DEBUG);
    ds.setIncludes(defaultPatterns.getIncludePatterns(p));
    ds.setExcludes(defaultPatterns.getExcludePatterns(p));
    if (useDefaultExcludes) {
      ds.addDefaultExcludes();
    }
    ds.setCaseSensitive(isCaseSensitive);
  }
}
