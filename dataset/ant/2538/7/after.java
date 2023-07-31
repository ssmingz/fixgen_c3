class PlaceHold {
  public IPlanetDeploymentTool createIplanet() {
    getLogger().debug("iPlanet Application Server deployment tools");
    IPlanetDeploymentTool tool = new IPlanetDeploymentTool();
    tool.setTask(this);
    deploymentTools.add(tool);
    return tool;
  }
}
