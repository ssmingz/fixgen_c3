class PlaceHold {
  public String getExpression() throws TaskException {
    if (m_expression == null) {
      throw new TaskException("Expression attribute is missing.");
    }
    return m_expression;
  }
}
