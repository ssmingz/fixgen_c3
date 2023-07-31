class PlaceHold {
  public void deployTask(final String name, final String location, final URL url)
      throws DeploymentException {
    final ZipFile zipFile = getZipFileFor(getFileFor(url));
    final Configuration taskdefs = loadConfiguration(zipFile, TSKDEF_FILE);
    try {
      final Configuration[] tasks = taskdefs.getChildren("task");
      for (int i = 0; i < tasks.length; i++) {
        if (tasks[i].getAttribute("name").equals(name)) {
          final DefaultComponentFactory factory = new DefaultComponentFactory(new URL[] {url});
          handleTask(tasks[i], url, factory);
          break;
        }
      }
    } catch (final ConfigurationException ce) {
      throw new DeploymentException("Malformed taskdefs.xml", ce);
    } catch (final Exception e) {
      throw new DeploymentException("Failed to deploy " + name, e);
    }
  }
}
