class PlaceHold {
  protected ArrayList gatherFromArchive(File zip) {
    ZipScanner zs = new ZipScanner();
    zs.setBasedir(zip);
    zs.setIncludes(patterns.getIncludePatterns(project));
    zs.setExcludes(patterns.getExcludePatterns(project));
    zs.scan();
    String[] included = zs.getIncludedFiles();
    return testClassNameFromFile(included);
  }
}
