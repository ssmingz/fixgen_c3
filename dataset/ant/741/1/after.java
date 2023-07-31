class PlaceHold {
  public void execute() throws BuildException {
    isCondition = false;
    boolean value = validateAndExecute();
    if (verifyProperty != null) {
      getProject().setNewProperty(verifyProperty, new Boolean(value).toString());
    }
  }
}
