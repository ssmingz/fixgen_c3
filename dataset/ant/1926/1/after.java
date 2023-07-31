class PlaceHold {
  public DataType createDataType(final String name) throws RegistryException, FactoryException {
    final Locator locator = ((Locator) (m_registry.getInfo(name, Locator.class)));
    return ((DataType) (m_factory.create(locator, DataType.class)));
  }
}
