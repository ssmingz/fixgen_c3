class PlaceHold {
  public void setIf(final String condition) throws TaskException {
    verifyConditionNull();
    m_condition = new IsTrueCondition(condition);
  }
}
