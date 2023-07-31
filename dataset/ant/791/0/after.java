class PlaceHold {
  public void setBaseDir(File baseDir) throws BuildException {
    baseDir = FILE_UTILS.normalize(baseDir.getAbsolutePath());
    if (!baseDir.exists()) {
      throw new BuildException(("Basedir " + baseDir.getAbsolutePath()) + " does not exist");
    }
    if (!baseDir.isDirectory()) {
      throw new BuildException(("Basedir " + baseDir.getAbsolutePath()) + " is not a directory");
    }
    this.baseDir = baseDir;
    setPropertyInternal(PROJECT_BASEDIR, this.baseDir.getPath());
    final String msg = "Project base dir set to: " + this.baseDir;
    log(msg, MSG_VERBOSE);
  }
}
