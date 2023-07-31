class PlaceHold {
  public void setTest(final String condition) throws TaskException {
    verifyConditionNull();
    m_condition = new IsTrueCondition(condition);
  }
}
