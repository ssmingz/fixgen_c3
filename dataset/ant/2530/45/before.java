class PlaceHold {
  protected void touch() throws TaskException {
    if (file != null) {
      if (!file.exists()) {
        log("Creating " + file, MSG_INFO);
        try {
          FileOutputStream fos = new FileOutputStream(file);
          fos.write(new byte[0]);
          fos.close();
        } catch (IOException ioe) {
          throw new TaskException("Could not create " + file, ioe);
        }
      }
    }
    if ((millis >= 0) && (getProject().getJavaVersion() == Project.JAVA_1_1)) {
      log("modification time of files cannot be set in JDK 1.1", MSG_WARN);
      return;
    }
    boolean resetMillis = false;
    if (millis < 0) {
      resetMillis = true;
      millis = System.currentTimeMillis();
    }
    if (file != null) {
      touch(file);
    }
    for (int i = 0; i < filesets.size(); i++) {
      FileSet fs = ((FileSet) (filesets.elementAt(i)));
      DirectoryScanner ds = fs.getDirectoryScanner(getProject());
      File fromDir = fs.getDir(getProject());
      String[] srcFiles = ds.getIncludedFiles();
      String[] srcDirs = ds.getIncludedDirectories();
      for (int j = 0; j < srcFiles.length; j++) {
        touch(new File(fromDir, srcFiles[j]));
      }
      for (int j = 0; j < srcDirs.length; j++) {
        touch(new File(fromDir, srcDirs[j]));
      }
    }
    if (resetMillis) {
      millis = -1;
    }
  }
}
