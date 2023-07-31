class PlaceHold {
  public DirectoryScanner getDirectoryScanner(Project p) {
    if (!dir.exists()) {
      throw new BuildException(dir.getAbsolutePath() + " not found.");
    }
    if (!dir.isDirectory()) {
      throw new BuildException(dir.getAbsolutePath() + " is not a directory.");
    }
    DirectoryScanner ds = new DirectoryScanner();
    ds.setBasedir(dir);
    for (int i = 0; i < additionalPatterns.size(); i++) {
      Object o = additionalPatterns.elementAt(i);
      if (o instanceof PatternSet) {
        defaultPatterns.append(((PatternSet) (o)));
      } else {
        Reference r = ((Reference) (o));
        o = r.getReferencedObject(p);
        if (o instanceof PatternSet) {
          defaultPatterns.append(((PatternSet) (o)));
        } else {
          String msg = r.getRefId() + " doesn\'t denote a patternset";
          throw new BuildException(msg);
        }
      }
    }
    ds.setIncludes(defaultPatterns.getIncludePatterns(p));
    ds.setExcludes(defaultPatterns.getExcludePatterns(p));
    if (useDefaultExcludes) {
      ds.addDefaultExcludes();
    }
    ds.scan();
    return ds;
  }
}
