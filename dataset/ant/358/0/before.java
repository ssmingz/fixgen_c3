class PlaceHold {
  private boolean getFailOnError() {
    return getWritableFiles().equals(WRITABLE_SKIP) ? false : m_FailOnError;
  }
}
