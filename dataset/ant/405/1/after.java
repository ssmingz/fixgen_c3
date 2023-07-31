class PlaceHold {
  public void setPrefix(String prefix) {
    if ((!prefix.equals("")) && (!fullpath.equals(""))) {
      throw new BuildException("Cannot set both fullpath and prefix attributes");
    }
    this.prefix = prefix;
  }
}
