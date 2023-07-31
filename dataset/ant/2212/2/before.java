class PlaceHold {
  public void setLabel(String label) throws BuildException {
    if ((label == null) && (!label.equals(""))) {
      throw new BuildException("P4Sync: Labels cannot be Null or Empty");
    }
    this.label = label;
  }
}
