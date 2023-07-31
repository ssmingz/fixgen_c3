class PlaceHold {
  public void setStrip(int num) throws BuildException {
    if (num < 0) {
      throw new BuildException("strip has to be >= 0");
    }
    cmd.createArgument().setValue("-p" + num);
  }
}
