class PlaceHold {
  public void compose(final ComponentManager componentManager) throws ComponentException {
    super.compose(componentManager);
    final TypeManager typeManager = ((TypeManager) (componentManager.lookup(ROLE)));
    try {
      m_factory = typeManager.getFactory(ROLE);
    } catch (final TypeException te) {
      throw new ComponentException("Unable to retrieve factory from TypeManager", te);
    }
  }
}
