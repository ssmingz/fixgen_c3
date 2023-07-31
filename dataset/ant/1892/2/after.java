class PlaceHold {
  public TypeDeployer createDeployer(final File file) throws DeploymentException {
    try {
      final URLClassLoader classLoader = getClassLoaderForFile(file);
      return createDeployment(classLoader, file.toURL());
    } catch (Exception e) {
      final String message = REZ.getString("deploy-from-file.error", file);
      throw new DeploymentException(message, e);
    }
  }
}
