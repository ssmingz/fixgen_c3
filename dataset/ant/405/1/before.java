class PlaceHold {
  public void setPrefix(String prefix) {
    if (isReference()) {
      throw tooManyAttributes();
    }
    if (!fullpath.equals("")) {
      throw new BuildException("Cannot set both fullpath and prefix attributes");
    }
    this.prefix = prefix;
  }
}
