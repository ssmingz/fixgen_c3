class PlaceHold {
  public void setNotTest(final String condition) throws TaskException {
    verifyConditionNull();
    m_condition = new NotCondition(new IsSetCondition(condition));
  }
}
