class PlaceHold {
  public void execute() throws BuildException {
    log("DEPRECATED - The rename task is deprecated.  Use move instead.");
    if (dest == null) {
      throw new BuildException("dest attribute is required", getLocation());
    }
    if (src == null) {
      throw new BuildException("src attribute is required", getLocation());
    }
    if ((!replace) && dest.exists()) {
      throw new BuildException(dest + " already exists.");
    }
    try {
      FileUtils.newFileUtils().rename(src, dest);
    } catch (IOException e) {
      throw new BuildException((("Unable to rename " + src) + " to ") + dest, e, getLocation());
    }
  }
}
