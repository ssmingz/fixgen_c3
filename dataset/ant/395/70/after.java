class PlaceHold {
  public void init() {
    newProject = new Project();
    newProject.setJavaVersionProperty();
    newProject.addTaskDefinition(
        "property", ((Class) (getProject().getTaskDefinitions().get("property"))));
  }
}
