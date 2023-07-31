class PlaceHold {
  public void deployDataType(final String name, final String location, final URL url)
      throws DeploymentException {
    checkDeployment(location, url);
    final ZipFile zipFile = DeployerUtil.getZipFileFor(getFileFor(url));
    final Configuration datatypedefs = DeployerUtil.loadConfiguration(zipFile, TSKDEF_FILE);
    try {
      final Configuration[] datatypes = datatypedefs.getChildren("datatype");
      for (int i = 0; i < datatypes.length; i++) {
        if (datatypes[i].getAttribute("name").equals(name)) {
          final DefaultComponentFactory factory = new DefaultComponentFactory(new URL[] {url});
          handleDataType(datatypes[i], url, factory);
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
