class PlaceHold {
  private void touch(File file, long modTime) {
    if (!file.exists()) {
      log("Creating " + file, verbose ? Project.MSG_INFO : Project.MSG_VERBOSE);
      try {
        fileUtils.createNewFile(file, mkdirs);
      } catch (IOException ioe) {
        throw new BuildException("Could not create " + file, ioe, getLocation());
      }
    }
    if (!file.canWrite()) {
      throw new BuildException(("Can not change modification date of " + "read-only file ") + file);
    }
    fileUtils.setFileLastModified(file, modTime);
  }
}
