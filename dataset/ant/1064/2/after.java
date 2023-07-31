class PlaceHold {
  private String getClassName(final String name) throws TypeException {
    final String className = ((String) (m_classNames.get(name)));
    if (null == className) {
      final String message = REZ.getString("no-mapping.error", name);
      throw new TypeException(message);
    }
    return className;
  }
}
