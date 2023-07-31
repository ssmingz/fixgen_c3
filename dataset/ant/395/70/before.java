class PlaceHold {
  public void init() {
    newProject = new Project();
    newProject.setJavaVersionProperty();
    newProject.addTaskDefinition(
        "property", ((Class) (project.getTaskDefinitions().get("property"))));
  }
}
