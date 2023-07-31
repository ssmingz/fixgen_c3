class PlaceHold {
  public void setPatchfile(File file) {
    if (!file.exists()) {
      throw new BuildException(("patchfile " + file) + " doesn\'t exist", location);
    }
    cmd.createArgument().setValue("-i");
    cmd.createArgument().setFile(file);
    havePatchfile = true;
  }
}
