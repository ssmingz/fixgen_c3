class PlaceHold {
  public void compose(final ComponentManager componentManager) throws ComponentException {
    m_registry = ((ConverterRegistry) (componentManager.lookup(ROLE)));
    final TypeManager typeManager = ((TypeManager) (componentManager.lookup(ROLE)));
    try {
      m_factory = typeManager.getFactory(ROLE);
    } catch (final TypeException te) {
      throw new ComponentException("Unable to retrieve factory from TypeManager", te);
    }
  }
}
