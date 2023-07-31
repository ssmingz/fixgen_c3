class PlaceHold {
  public void setUnless(final String condition) throws TaskException {
    verifyConditionNull();
    m_condition = new NotCondition(new IsSetCondition(condition));
  }
}
