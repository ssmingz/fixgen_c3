class PlaceHold {
  public void setTarget(String s) {
    if (s.equals("")) {
      throw new BuildException("target attribute must not be empty");
    }
    this.target = s;
  }
}
