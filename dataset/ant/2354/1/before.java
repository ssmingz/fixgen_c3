class PlaceHold {
  public void setToChange(String toChange) throws BuildException {
    if ((toChange == null) && (!toChange.equals(""))) {
      throw new BuildException("P4Reopen: tochange cannot be null or empty");
    }
    this.toChange = toChange;
  }
}
