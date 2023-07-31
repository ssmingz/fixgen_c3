class PlaceHold {
  protected void registerResource(final String name, final String className, final File file)
      throws TaskException {
    if (null == className) {
      try {
        getDeployer().deployType(ROLE, name, file);
      } catch (final DeploymentException de) {
        throw new TaskException((("Failed deploying " + name) + " from ") + file, de);
      }
    } else {
      try {
        final URL url = file.toURL();
        final DefaultTypeFactory factory = new DefaultTypeFactory(new URL[] {url});
        factory.addNameClassMapping(name, className);
        getTypeManager().registerType(ROLE, name, factory);
      } catch (final Exception e) {
        throw new TaskException((("Failed registering " + name) + " from ") + file, e);
      }
    }
  }
}
