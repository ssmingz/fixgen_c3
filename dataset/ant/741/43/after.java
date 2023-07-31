class PlaceHold {
  public void execute() throws BuildException {
    log("DEPRECATED - The copyfile task is deprecated.  Use copy instead.");
    if (srcFile == null) {
      throw new BuildException("The src attribute must be present.", location);
    }
    if (!srcFile.exists()) {
      throw new BuildException(("src " + srcFile.toString()) + " does not exist.", location);
    }
    if (destFile == null) {
      throw new BuildException("The dest attribute must be present.", location);
    }
    if (srcFile.equals(destFile)) {
      log("Warning: src == dest", MSG_WARN);
    }
    if (forceOverwrite || (srcFile.lastModified() > destFile.lastModified())) {
      try {
        getProject().copyFile(srcFile, destFile, filtering, forceOverwrite);
      } catch (IOException ioe) {
        String msg =
            (("Error copying file: " + srcFile.getAbsolutePath()) + " due to ") + ioe.getMessage();
        throw new BuildException(msg);
      }
    }
  }
}
