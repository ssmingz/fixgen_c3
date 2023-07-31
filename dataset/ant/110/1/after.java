class PlaceHold {
  public void deployConverter(String name, String location, URL url) throws DeploymentException {
    final ZipFile zipFile = getZipFileFor(getFileFor(url));
    final Configuration taskdefs = loadConfiguration(zipFile, TSKDEF_FILE);
    try {
      final Configuration[] converters = taskdefs.getChildren("converter");
      for (int i = 0; i < converters.length; i++) {
        if (converters[i].getAttribute("classname").equals(name)) {
          final DefaultComponentFactory factory = new DefaultComponentFactory(new URL[] {url});
          handleConverter(converters[i], url, factory);
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
