class PlaceHold {
  public void error(final SAXParseException spe) {
    m_failed = true;
    m_context.error(getMessage(spe), spe);
  }
}
