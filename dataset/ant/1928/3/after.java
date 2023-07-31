class PlaceHold {
  public void execute() throws BuildException {
    if (usedMatchingTask) {
      log(
          "DEPRECATED - Use of the implicit FileSet is deprecated.  Use a nested fileset element"
              + " instead.");
    }
    if (((file == null) && (dir == null)) && (filesets.size() == 0)) {
      throw new BuildException(
          "At least one of the file or dir attributes, or a fileset element, must be set.");
    }
    if (file != null) {
      if (file.exists()) {
        if (file.isDirectory()) {
          log(
              ("Directory " + file.getAbsolutePath())
                  + " cannot be removed using the file attribute.  Use dir instead.");
        } else {
          log("Deleting: " + file.getAbsolutePath());
          if ((!file.delete()) && (!quiet)) {
            throw new BuildException("Unable to delete file " + file.getAbsolutePath());
          }
        }
      } else {
        log(("Could not find file " + file.getAbsolutePath()) + " to delete.", MSG_VERBOSE);
      }
    }
    if ((((dir != null) && dir.exists()) && dir.isDirectory()) && (!usedMatchingTask)) {
      if (verbosity == Project.MSG_VERBOSE) {
        log("Deleting directory " + dir.getAbsolutePath());
      }
      removeDir(dir);
    }
    for (int i = 0; i < filesets.size(); i++) {
      FileSet fs = ((FileSet) (filesets.elementAt(i)));
      DirectoryScanner ds = fs.getDirectoryScanner(project);
      String[] files = ds.getIncludedFiles();
      String[] dirs = ds.getIncludedDirectories();
      removeFiles(fs.getDir(project), files, dirs);
    }
    if (usedMatchingTask && (dir != null)) {
      DirectoryScanner ds = super.getDirectoryScanner(dir);
      String[] files = ds.getIncludedFiles();
      String[] dirs = ds.getIncludedDirectories();
      removeFiles(dir, files, dirs);
    }
  }
}
