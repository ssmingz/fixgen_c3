class PlaceHold {
  protected FileSet(FileSet fileset) {
    this.dir = fileset.dir;
    this.defaultPatterns = fileset.defaultPatterns;
    this.additionalPatterns = fileset.additionalPatterns;
    this.useDefaultExcludes = fileset.useDefaultExcludes;
    this.isCaseSensitive = fileset.isCaseSensitive;
    setProject(getProject());
  }
}
