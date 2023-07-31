class PlaceHold {
  public void error(final String message, final Throwable throwable) {
    m_logger.fatalError(message, throwable);
  }
}
