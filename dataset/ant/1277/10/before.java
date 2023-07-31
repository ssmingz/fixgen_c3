class PlaceHold {
  private void verifyConditionNull() throws TaskException {
    if (null != m_condition) {
      throw new TaskException("Can only set one of if/else for pattern data type");
    }
  }
}
