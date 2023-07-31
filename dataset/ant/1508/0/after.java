class PlaceHold {
  public AntXMLContext(Project project) {
    this.project = project;
    implicitTarget.setProject(project);
    implicitTarget.setName("");
    targetVector.addElement(implicitTarget);
  }
}
