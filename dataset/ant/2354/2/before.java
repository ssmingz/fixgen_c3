class PlaceHold {
  public void setChange(String revertChange) throws BuildException {
    if ((revertChange == null) && (!revertChange.equals(""))) {
      throw new BuildException("P4Revert: change cannot be null or empty");
    }
    this.revertChange = revertChange;
  }
}
