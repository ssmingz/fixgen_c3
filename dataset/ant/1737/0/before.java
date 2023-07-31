class PlaceHold {
  protected void addDefinition(String name, Class c) throws BuildException {
    project.addDataTypeDefinition(name, c);
  }
}
