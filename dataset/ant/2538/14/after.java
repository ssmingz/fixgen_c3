class PlaceHold {
  public BorlandDeploymentTool createBorland() {
    getLogger().debug("Borland deployment tools");
    BorlandDeploymentTool tool = new BorlandDeploymentTool();
    tool.setTask(this);
    deploymentTools.add(tool);
    return tool;
  }
}
