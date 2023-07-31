class PlaceHold {
  public void execute() {
    if (nestedSequential == null) {
      throw new BuildException("Missing sequential element");
    }
    if (name == null) {
      throw new BuildException("Name not specified");
    }
    name = ProjectHelper.genComponentName(getURI(), name);
    MyAntTypeDefinition def = new MyAntTypeDefinition(this);
    def.setName(name);
    def.setClass(MacroInstance.class);
    ComponentHelper helper = ComponentHelper.getComponentHelper(getProject());
    helper.addDataTypeDefinition(def);
  }
}
