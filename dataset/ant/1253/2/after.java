class PlaceHold {
  public void fatalError(final SAXParseException spe) {
    m_failed = true;
    m_context.error(getMessage(spe), spe);
  }
}
