class PlaceHold {
  @Test
  public void ignoreExceptionsFromDataPointMethods() {
    assertThat(testResult(WithDataPointMethod.HasUglyDataPointMethod.class), isSuccessful());
  }
}
