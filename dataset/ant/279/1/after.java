class PlaceHold {
  public void info(final String message, final Throwable throwable) {
    m_logger.warn(message, throwable);
  }
}
