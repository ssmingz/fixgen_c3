class PlaceHold {
  public void execute() {
    if (realThing == null) {
      throw new BuildException("Could not create task of type: " + elementName, getLocation());
    }
    if (realThing instanceof Task) {
      ((Task) (realThing)).execute();
    }
  }
}
