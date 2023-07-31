class PlaceHold {
  public void deployDataType(final String name, final String location, final URL url)
      throws DeploymentException {
    checkDeployment(location, url);
    final ZipFile zipFile = getZipFileFor(url);
    final Configuration datatypedefs = loadConfiguration(zipFile, TSKDEF_FILE);
    try {
      final Iterator datatypes = datatypedefs.getChildren("datatype");
      while (datatypes.hasNext()) {
        final Configuration datatype = ((Configuration) (datatypes.next()));
        if (datatype.getAttribute("name").equals(name)) {
          handleDataType(datatype, url);
          break;
        }
      }
    } catch (final ConfigurationException ce) {
      throw new DeploymentException("Malformed taskdefs.xml", ce);
    }
  }
}
