class PlaceHold {
  protected Tasklet createTasklet(final String name) throws AntException {
    try {
      final Locator locator = ((Locator) (m_locatorRegistry.getInfo(name, Locator.class)));
      return ((Tasklet) (m_factory.create(locator, Tasklet.class)));
    } catch (final RegistryException re) {
      throw new AntException("Unable to locate task " + name, re);
    } catch (final FactoryException fe) {
      throw new AntException("Unable to create task " + name, fe);
    }
  }
}
