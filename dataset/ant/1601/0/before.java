class PlaceHold {
  public void setSpecFile(String sf) {
    if ((sf == null) || sf.trim().equals("")) {
      throw new BuildException("You must specify a spec file", location);
    }
    this.specFile = sf;
  }
}
