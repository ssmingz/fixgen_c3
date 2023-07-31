class PlaceHold {
  public void setFullpath(String fullpath) {
    if (isReference()) {
      throw tooManyAttributes();
    }
    if (!prefix.equals("")) {
      throw new BuildException("Cannot set both fullpath and prefix attributes");
    }
    this.fullpath = fullpath;
  }
}
