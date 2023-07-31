class PlaceHold {
  public void addContent(final String message) {
    System.out.println(message);
    checkNullMessage();
    m_message = message;
  }
}
