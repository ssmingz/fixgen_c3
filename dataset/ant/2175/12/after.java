class PlaceHold {
  public void execute() throws TaskException {
    validateAttributes();
    if (file != null) {
      if (file.exists()) {
        if (destFile == null) {
          destFile = new File(destDir, file.getName());
        }
        if (forceOverwrite || (file.lastModified() > destFile.lastModified())) {
          fileCopyMap.put(file.getAbsolutePath(), destFile.getAbsolutePath());
        } else {
          log(((file + " omitted as ") + destFile) + " is up to date.", MSG_VERBOSE);
        }
      } else {
        String message = ("Could not find file " + file.getAbsolutePath()) + " to copy.";
        getLogger().info(message);
        throw new TaskException(message);
      }
    }
    for (int i = 0; i < filesets.size(); i++) {
      FileSet fs = ((FileSet) (filesets.elementAt(i)));
      DirectoryScanner ds = fs.getDirectoryScanner(project);
      File fromDir = fs.getDir(project);
      String[] srcFiles = ds.getIncludedFiles();
      String[] srcDirs = ds.getIncludedDirectories();
      boolean isEverythingIncluded = ds.isEverythingIncluded();
      if ((isEverythingIncluded && (!flatten)) && (mapperElement == null)) {
        completeDirMap.put(fromDir, destDir);
      }
      scan(fromDir, destDir, srcFiles, srcDirs);
    }
    doFileOperations();
    if (destFile != null) {
      destDir = null;
    }
  }
}
