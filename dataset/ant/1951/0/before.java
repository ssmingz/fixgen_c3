class PlaceHold {
  public void execute() throws TaskException {
    if ((sourceFileSets.size() == 0) && (sourceFileLists.size() == 0)) {
      throw new TaskException("At least one <srcfileset> or <srcfilelist> element must be set");
    }
    if ((targetFileSets.size() == 0) && (targetFileLists.size() == 0)) {
      throw new TaskException(
          "At least one <targetfileset> or <targetfilelist> element must be set");
    }
    long now = new Date().getTime();
    if (Os.isFamily("windows")) {
      now += 2000;
    }
    ArrayList allTargets = new ArrayList();
    Iterator enumTargetSets = targetFileSets.iterator();
    while (enumTargetSets.hasNext()) {
      FileSet targetFS = ((FileSet) (enumTargetSets.next()));
      DirectoryScanner targetDS = ScannerUtil.getDirectoryScanner(targetFS);
      String[] targetFiles = targetDS.getIncludedFiles();
      for (int i = 0; i < targetFiles.length; i++) {
        File dest = new File(targetFS.getDir(), targetFiles[i]);
        allTargets.add(dest);
        if (dest.lastModified() > now) {
          getLogger().warn(("Warning: " + targetFiles[i]) + " modified in the future.");
        }
      }
    }
    boolean upToDate = true;
    Iterator enumTargetLists = targetFileLists.iterator();
    while (enumTargetLists.hasNext()) {
      FileList targetFL = ((FileList) (enumTargetLists.next()));
      String[] targetFiles = targetFL.getFiles();
      for (int i = 0; i < targetFiles.length; i++) {
        File dest = new File(targetFL.getDir(), targetFiles[i]);
        if (!dest.exists()) {
          getLogger().debug(targetFiles[i] + " does not exist.");
          upToDate = false;
          continue;
        } else {
          allTargets.add(dest);
        }
        if (dest.lastModified() > now) {
          getLogger().warn(("Warning: " + targetFiles[i]) + " modified in the future.");
        }
      }
    }
    if (upToDate) {
      Iterator enumSourceSets = sourceFileSets.iterator();
      while (upToDate && enumSourceSets.hasNext()) {
        FileSet sourceFS = ((FileSet) (enumSourceSets.next()));
        DirectoryScanner sourceDS = ScannerUtil.getDirectoryScanner(sourceFS);
        String[] sourceFiles = sourceDS.getIncludedFiles();
        for (int i = 0; upToDate && (i < sourceFiles.length); i++) {
          File src = new File(sourceFS.getDir(), sourceFiles[i]);
          if (src.lastModified() > now) {
            getLogger().warn(("Warning: " + sourceFiles[i]) + " modified in the future.");
          }
          Iterator enumTargets = allTargets.iterator();
          while (upToDate && enumTargets.hasNext()) {
            File dest = ((File) (enumTargets.next()));
            if (src.lastModified() > dest.lastModified()) {
              getLogger()
                  .debug((dest.getPath() + " is out of date with respect to ") + sourceFiles[i]);
              upToDate = false;
            }
          }
        }
      }
    }
    if (upToDate) {
      Iterator enumSourceLists = sourceFileLists.iterator();
      while (upToDate && enumSourceLists.hasNext()) {
        FileList sourceFL = ((FileList) (enumSourceLists.next()));
        String[] sourceFiles = sourceFL.getFiles();
        int i = 0;
        do {
          File src = new File(sourceFL.getDir(), sourceFiles[i]);
          if (src.lastModified() > now) {
            getLogger().warn(("Warning: " + sourceFiles[i]) + " modified in the future.");
          }
          if (!src.exists()) {
            getLogger().debug(sourceFiles[i] + " does not exist.");
            upToDate = false;
            break;
          }
          Iterator enumTargets = allTargets.iterator();
          while (upToDate && enumTargets.hasNext()) {
            File dest = ((File) (enumTargets.next()));
            if (src.lastModified() > dest.lastModified()) {
              getLogger()
                  .debug((dest.getPath() + " is out of date with respect to ") + sourceFiles[i]);
              upToDate = false;
            }
          }
        } while (upToDate && ((++i) < sourceFiles.length));
      }
    }
    if (!upToDate) {
      getLogger().debug("Deleting all target files. ");
      for (Iterator e = allTargets.iterator(); e.hasNext(); ) {
        File fileToRemove = ((File) (e.next()));
        getLogger().debug("Deleting file " + fileToRemove.getAbsolutePath());
        fileToRemove.delete();
      }
    }
  }
}
