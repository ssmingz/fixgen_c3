class PlaceHold {
  public DataType createDataType(final String name) throws RegistryException, FactoryException {
    final Locator locator = m_registry.getLocator(name);
    return ((DataType) (m_factory.create(locator, DataType.class)));
  }
}
