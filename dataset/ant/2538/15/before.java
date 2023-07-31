class PlaceHold {
  public BorlandDeploymentTool createBorland() {
    log("Borland deployment tools", MSG_VERBOSE);
    BorlandDeploymentTool tool = new BorlandDeploymentTool();
    tool.setTask(this);
    deploymentTools.add(tool);
    return tool;
  }
}
