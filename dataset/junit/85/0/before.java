class PlaceHold {
  @Test
  public void ignoreExceptionsFromDataPointMethods() {
    assertThat(failures(WithDataPointMethod.HasUglyDataPointMethod.class), empty());
  }
}
