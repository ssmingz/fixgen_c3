class PlaceHold {
  public void warn(final String message, final Throwable throwable) {
    m_logger.error(message, throwable);
  }
}
