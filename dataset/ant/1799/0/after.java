class PlaceHold {
  public void execute() throws BuildException {
    project.log("Deleting: " + dir.getAbsolutePath());
    if (dir.exists()) {
      if (!dir.isDirectory()) {
        dir.delete();
        if (dir.exists()) {
          throw new BuildException("Unable to delete file " + dir.getAbsolutePath());
        }
        return;
      }
      try {
        removeDir(dir);
      } catch (IOException ioe) {
        String msg = "Unable to delete " + dir.getAbsolutePath();
        throw new BuildException(msg);
      }
    }
  }
}
