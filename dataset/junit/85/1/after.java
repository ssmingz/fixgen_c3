class PlaceHold {
  @Test
  public void pickUpDataPointMethods() {
    assertThat(testResult(WithDataPointMethod.HasDataPointMethod.class), isSuccessful());
  }
}
