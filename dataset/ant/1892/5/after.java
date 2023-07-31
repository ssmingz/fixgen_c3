class PlaceHold {
  public void execute() throws TaskException {
    if (null == m_lib) {
      final String message = REZ.getString("import.no-lib.error");
      throw new TaskException(message);
    }
    try {
      final Deployer deployer = ((Deployer) (getService(Deployer.class)));
      final TypeDeployer typeDeployer = deployer.createDeployer(m_lib);
      typeDeployer.deployAll();
    } catch (final DeploymentException de) {
      final String message = REZ.getString("import.no-deploy.error");
      throw new TaskException(message, de);
    }
  }
}
