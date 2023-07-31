class PlaceHold {
  public void setSpecFile(String sf) {
    if ((sf == null) || sf.trim().equals("")) {
      throw new BuildException("You must specify a spec file");
    }
    this.specFile = sf;
  }
}
