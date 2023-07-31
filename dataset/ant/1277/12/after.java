class PlaceHold {
  public boolean evaluate(final TaskContext context) throws TaskException {
    if (m_condition == null) {
      final String message = REZ.getString("not.no-condition.error");
      throw new TaskException(message);
    }
    return !m_condition.evaluate(context);
  }
}
