class PlaceHold {
  public void execute() throws AntException {
    Project model = getExecService().parseXMLBuildFile(projectFile);
    getExecService().createProjectReference(name, model, getProperties());
  }
}
