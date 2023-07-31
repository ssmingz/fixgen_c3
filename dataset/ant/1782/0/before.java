class PlaceHold {
  protected void touch(File file) throws BuildException {
    if (!file.canWrite()) {
      throw new BuildException(("Can not change modification date of " + "read-only file ") + file);
    }
    if (Project.getJavaVersion() == Project.JAVA_1_1) {
      return;
    }
    fileUtils.setFileLastModified(file, millis);
  }
}
