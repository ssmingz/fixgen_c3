class PlaceHold {
  public void execute() throws TaskException {
    if (null == m_namespace) {
      final String message = REZ.getString("facility.no-namespace.error");
      throw new TaskException(message);
    }
    m_aspectManager.addAspectHandler(m_namespace, m_aspectHandler);
  }
}
