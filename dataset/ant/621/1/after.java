class PlaceHold {
  private void verifyConditionNull() throws TaskException {
    if (null != m_condition) {
      final String message = REZ.getString("pattern.ifelse-duplicate.error");
      throw new TaskException(message);
    }
  }
}
