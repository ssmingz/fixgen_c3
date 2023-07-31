class PlaceHold {
  public Object create(final String name) throws TypeException {
    final String className = getClassName(name);
    try {
      return getClassLoader().loadClass(className).newInstance();
    } catch (final Exception e) {
      throw new TypeException(("Unable to instantiate '" + name) + "'", e);
    }
  }
}
