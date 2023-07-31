class PlaceHold {
  public void dispatchAspectSettings(
      final String name, final Parameters parameters, final Configuration[] elements)
      throws TaskException {
    final AspectHandler handler = ((AspectHandler) (m_aspectMap.get(name)));
    if (null == handler) {
      final String message = REZ.getString("no.aspect", name);
      throw new TaskException(message);
    }
    handler.aspectSettings(parameters, elements);
  }
}
