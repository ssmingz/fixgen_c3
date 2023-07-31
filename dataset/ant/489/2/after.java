class PlaceHold {
  public Object create(final String name) throws TypeException {
    final String className = getClassName(name);
    try {
      return getClassLoader().loadClass(className).newInstance();
    } catch (final Exception e) {
      final String message = REZ.getString("no-instantiate.error", name);
      throw new TypeException(message, e);
    }
  }
}
