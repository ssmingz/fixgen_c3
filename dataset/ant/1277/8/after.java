class PlaceHold {
  public void compose(final ComponentManager componentManager) throws ComponentException {
    m_registry = ((ConverterRegistry) (componentManager.lookup(ROLE)));
    final TypeManager typeManager = ((TypeManager) (componentManager.lookup(ROLE)));
    try {
      m_factory = typeManager.getFactory(ROLE);
    } catch (final TypeException te) {
      final String message = REZ.getString("no-converter-factory.error");
      throw new ComponentException(message, te);
    }
  }
}
