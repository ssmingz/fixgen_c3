class PlaceHold {
  public void execute() throws BuildException {
    if (countConditions() > 1) {
      throw new BuildException("You must not nest more than one condition into <condition>");
    }
    if (countConditions() < 1) {
      throw new BuildException("You must nest a condition into <condition>");
    }
    Condition c = ((Condition) (getConditions().nextElement()));
    if (c.eval()) {
      getProject().setNewProperty(property, value);
    }
  }
}
