class PlaceHold {
  public void execute() throws BuildException {
    if ((sourceFileSets.size() == 0) && (sourceFileLists.size() == 0)) {
      throw new BuildException(
          "At least one <srcfileset> or <srcfilelist>" + " element must be set");
    }
    if ((targetFileSets.size() == 0) && (targetFileLists.size() == 0)) {
      throw new BuildException(
          "At least one <targetfileset> or" + " <targetfilelist> element must be set");
    }
    long now = new Date().getTime();
    now += FileUtils.getFileUtils().getFileTimestampGranularity();
    Vector allTargets = new Vector();
    long oldestTargetTime = 0;
    File oldestTarget = null;
    Enumeration enumTargetSets = targetFileSets.elements();
    while (enumTargetSets.hasMoreElements()) {
      FileSet targetFS = ((FileSet) (enumTargetSets.nextElement()));
      if (!targetFS.getDir(getProject()).exists()) {
        continue;
      }
      DirectoryScanner targetDS = targetFS.getDirectoryScanner(getProject());
      String[] targetFiles = targetDS.getIncludedFiles();
      for (int i = 0; i < targetFiles.length; i++) {
        File dest = new File(targetFS.getDir(getProject()), targetFiles[i]);
        allTargets.addElement(dest);
        if (dest.lastModified() > now) {
          log(("Warning: " + targetFiles[i]) + " modified in the future.", MSG_WARN);
        }
        if ((oldestTarget == null) || (dest.lastModified() < oldestTargetTime)) {
          oldestTargetTime = dest.lastModified();
          oldestTarget = dest;
        }
      }
    }
    boolean upToDate = true;
    Enumeration enumTargetLists = targetFileLists.elements();
    while (enumTargetLists.hasMoreElements()) {
      FileList targetFL = ((FileList) (enumTargetLists.nextElement()));
      String[] targetFiles = targetFL.getFiles(getProject());
      for (int i = 0; i < targetFiles.length; i++) {
        File dest = new File(targetFL.getDir(getProject()), targetFiles[i]);
        if (!dest.exists()) {
          log(targetFiles[i] + " does not exist.", MSG_VERBOSE);
          upToDate = false;
          continue;
        } else {
          allTargets.addElement(dest);
        }
        if (dest.lastModified() > now) {
          log(("Warning: " + targetFiles[i]) + " modified in the future.", MSG_WARN);
        }
        if ((oldestTarget == null) || (dest.lastModified() < oldestTargetTime)) {
          oldestTargetTime = dest.lastModified();
          oldestTarget = dest;
        }
      }
    }
    if (oldestTarget != null) {
      log(oldestTarget + " is oldest target file", MSG_VERBOSE);
    } else {
      upToDate = false;
    }
    if (upToDate) {
      Enumeration enumSourceLists = sourceFileLists.elements();
      while (upToDate && enumSourceLists.hasMoreElements()) {
        FileList sourceFL = ((FileList) (enumSourceLists.nextElement()));
        String[] sourceFiles = sourceFL.getFiles(getProject());
        for (int i = 0; upToDate && (i < sourceFiles.length); i++) {
          File src = new File(sourceFL.getDir(getProject()), sourceFiles[i]);
          if (src.lastModified() > now) {
            log(("Warning: " + sourceFiles[i]) + " modified in the future.", MSG_WARN);
          }
          if (!src.exists()) {
            log(sourceFiles[i] + " does not exist.", MSG_VERBOSE);
            upToDate = false;
            break;
          }
          if (src.lastModified() > oldestTargetTime) {
            upToDate = false;
            log((oldestTarget + " is out of date with respect to ") + sourceFiles[i], MSG_VERBOSE);
          }
        }
      }
    }
    if (upToDate) {
      Enumeration enumSourceSets = sourceFileSets.elements();
      while (upToDate && enumSourceSets.hasMoreElements()) {
        FileSet sourceFS = ((FileSet) (enumSourceSets.nextElement()));
        DirectoryScanner sourceDS = sourceFS.getDirectoryScanner(getProject());
        String[] sourceFiles = sourceDS.getIncludedFiles();
        for (int i = 0; upToDate && (i < sourceFiles.length); i++) {
          File src = new File(sourceFS.getDir(getProject()), sourceFiles[i]);
          if (src.lastModified() > now) {
            log(("Warning: " + sourceFiles[i]) + " modified in the future.", MSG_WARN);
          }
          if (src.lastModified() > oldestTargetTime) {
            upToDate = false;
            log((oldestTarget + " is out of date with respect to ") + sourceFiles[i], MSG_VERBOSE);
          }
        }
      }
    }
    if (!upToDate) {
      log("Deleting all target files. ", MSG_VERBOSE);
      for (Enumeration e = allTargets.elements(); e.hasMoreElements(); ) {
        File fileToRemove = ((File) (e.nextElement()));
        log("Deleting file " + fileToRemove.getAbsolutePath(), MSG_VERBOSE);
        fileToRemove.delete();
      }
    }
  }
}
