class PlaceHold {
  public void execute() throws TaskException {
    if (null == m_namespace) {
      throw new TaskException("Must specify namespace parameter");
    }
    m_aspectManager.addAspectHandler(m_namespace, m_aspectHandler);
  }
}
