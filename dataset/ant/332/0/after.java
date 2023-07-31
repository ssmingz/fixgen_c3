class PlaceHold {
  public void execute() {
    if (nestedTask == null) {
      throw new BuildException("Missing nested element");
    }
    if (name == null) {
      throw new BuildException("Name not specified");
    }
    name = ProjectHelper.genComponentName(getURI(), name);
    ComponentHelper helper = ComponentHelper.getComponentHelper(getProject());
    String componentName =
        ProjectHelper.genComponentName(nestedTask.getNamespace(), nestedTask.getTag());
    AntTypeDefinition def = helper.getDefinition(componentName);
    if (def == null) {
      throw new BuildException("Unable to find typedef " + componentName);
    }
    PreSetDefinition newDef = new PreSetDefinition(def, nestedTask);
    newDef.setName(name);
    helper.addDataTypeDefinition(newDef);
    log("defining preset " + name, MSG_VERBOSE);
  }
}
