class PlaceHold {
  public void dispatchAspectSettings(
      final String name, final Parameters parameters, final Configuration[] elements)
      throws TaskException {
    final AspectHandler handler = ((AspectHandler) (m_aspectMap.get(name)));
    if (null == handler) {
      throw new TaskException(("No such aspect with name '" + name) + "'");
    }
    handler.aspectSettings(parameters, elements);
  }
}
