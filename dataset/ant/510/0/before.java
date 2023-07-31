class PlaceHold {
  public boolean eval() {
    if (((sourceFileSets.size() == 0) && (sourceResources.size() == 0)) && (sourceFile == null)) {
      throw new BuildException(
          ("At least one srcfile or a nested " + "<srcfiles> or <srcresources> element ")
              + "must be set.");
    }
    if (((sourceFileSets.size() > 0) || (sourceResources.size() > 0)) && (sourceFile != null)) {
      throw new BuildException(
          ("Cannot specify both the srcfile " + "attribute and a nested <srcfiles> ")
              + "or <srcresources> element.");
    }
    if ((targetFile == null) && (mapperElement == null)) {
      throw new BuildException(
          "The targetfile attribute or a nested " + "mapper element must be set.");
    }
    if ((targetFile != null) && (!targetFile.exists())) {
      log(("The targetfile \"" + targetFile.getAbsolutePath()) + "\" does not exist.", MSG_VERBOSE);
      return false;
    }
    if ((sourceFile != null) && (!sourceFile.exists())) {
      throw new BuildException(sourceFile.getAbsolutePath() + " not found.");
    }
    boolean upToDate = true;
    if (sourceFile != null) {
      if (mapperElement == null) {
        upToDate = upToDate && (targetFile.lastModified() >= sourceFile.lastModified());
      } else {
        SourceFileScanner sfs = new SourceFileScanner(this);
        upToDate =
            upToDate
                && (sfs.restrict(
                            new String[] {sourceFile.getAbsolutePath()},
                            null,
                            null,
                            mapperElement.getImplementation())
                        .length
                    == 0);
      }
    }
    Enumeration e = sourceFileSets.elements();
    while (upToDate && e.hasMoreElements()) {
      FileSet fs = ((FileSet) (e.nextElement()));
      DirectoryScanner ds = fs.getDirectoryScanner(getProject());
      upToDate = upToDate && scanDir(fs.getDir(getProject()), ds.getIncludedFiles());
    }
    if (upToDate) {
      Resource[] r = sourceResources.listResources();
      upToDate =
          upToDate
              && (ResourceUtils.selectOutOfDateResources(this, r, getMapper(), getProject(), null)
                      .length
                  == 0);
    }
    return upToDate;
  }
}
