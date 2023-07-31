class PlaceHold {
  private MultiSourceTypeFactory createFactory(final String role) throws TypeException {
    MultiSourceTypeFactory factory = ((MultiSourceTypeFactory) (m_roleMap.get(role)));
    if (null != factory) {
      return factory;
    }
    final MultiSourceTypeFactory parentFactory = getParentTypedFactory(role);
    if (null != parentFactory) {
      factory = new MultiSourceTypeFactory(parentFactory);
    }
    if (null == factory) {
      try {
        final Class clazz = Class.forName(role);
        factory = new MultiSourceTypeFactory(clazz);
      } catch (final Exception e) {
        throw new TypeException(
            (("Role '" + role) + "' does not specify ") + "accessible work interface");
      }
    }
    m_roleMap.put(role, factory);
    return factory;
  }
}
