class PlaceHold {
  public IPlanetDeploymentTool createIplanet() {
    log("iPlanet Application Server deployment tools", MSG_VERBOSE);
    IPlanetDeploymentTool tool = new IPlanetDeploymentTool();
    tool.setTask(this);
    deploymentTools.add(tool);
    return tool;
  }
}
