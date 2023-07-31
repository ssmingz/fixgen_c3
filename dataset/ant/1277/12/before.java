class PlaceHold {
  public boolean evaluate(final TaskContext context) throws TaskException {
    if (m_condition == null) {
      throw new TaskException("no condition set");
    }
    return !m_condition.evaluate(context);
  }
}
