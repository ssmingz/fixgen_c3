class PlaceHold {
  protected void addDefinition(String name, Class c) throws BuildException {
    getProject().addTaskDefinition(name, c);
  }
}
