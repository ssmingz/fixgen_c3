class PlaceHold {
  public void setFullpath(String fullpath) {
    if ((!prefix.equals("")) && (!fullpath.equals(""))) {
      throw new BuildException("Cannot set both fullpath and prefix attributes");
    }
    this.fullpath = fullpath;
  }
}
