class PlaceHold {
  public void deployConverter(String name, String location, URL url) throws DeploymentException {
    checkDeployment(location, url);
    final ZipFile zipFile = getZipFileFor(url);
    final Configuration taskdefs = loadConfiguration(zipFile, TSKDEF_FILE);
    try {
      final Iterator converters = taskdefs.getChildren("converter");
      while (converters.hasNext()) {
        final Configuration converter = ((Configuration) (converters.next()));
        if (converter.getAttribute("classname").equals(name)) {
          handleConverter(converter, url);
          break;
        }
      }
    } catch (final ConfigurationException ce) {
      throw new DeploymentException("Malformed taskdefs.xml", ce);
    }
  }
}
