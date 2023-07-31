class PlaceHold {
  private String getClassName(final String name) throws TypeException {
    final String className = ((String) (m_classNames.get(name)));
    if (null == className) {
      throw new TypeException(("Malconfigured factory, no clasname for '" + name) + "'");
    }
    return className;
  }
}
