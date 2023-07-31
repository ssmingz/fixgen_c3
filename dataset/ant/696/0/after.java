class PlaceHold {
  public void setPatchfile(File file) {
    if (!file.exists()) {
      throw new BuildException(("patchfile " + file) + " doesn\'t exist", getLocation());
    }
    cmd.createArgument().setValue("-i");
    cmd.createArgument().setFile(file);
    havePatchfile = true;
  }
}
