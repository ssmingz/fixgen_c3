class PlaceHold {
  public void deployTasklet(final String name, final String location, final URL url)
      throws DeploymentException {
    checkDeployment(location, url);
    final ZipFile zipFile = DeployerUtil.getZipFileFor(getFileFor(url));
    final Configuration taskdefs = DeployerUtil.loadConfiguration(zipFile, TSKDEF_FILE);
    try {
      final Iterator tasks = taskdefs.getChildren("task");
      while (tasks.hasNext()) {
        final Configuration task = ((Configuration) (tasks.next()));
        if (task.getAttribute("name").equals(name)) {
          handleTasklet(task, url);
          break;
        }
      }
    } catch (final ConfigurationException ce) {
      throw new DeploymentException("Malformed taskdefs.xml", ce);
    }
  }
}
