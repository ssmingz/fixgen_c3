class PlaceHold {
  public void deployTask(final String name, final String location, final URL url)
      throws DeploymentException {
    checkDeployment(location, url);
    final ZipFile zipFile = DeployerUtil.getZipFileFor(getFileFor(url));
    final Configuration taskdefs = DeployerUtil.loadConfiguration(zipFile, TSKDEF_FILE);
    try {
      final Configuration[] tasks = taskdefs.getChildren("task");
      for (int i = 0; i < tasks.length; i++) {
        if (tasks[i].getAttribute("name").equals(name)) {
          handleTask(tasks[i], url);
          break;
        }
      }
    } catch (final ConfigurationException ce) {
      throw new DeploymentException("Malformed taskdefs.xml", ce);
    }
  }
}
