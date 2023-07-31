class PlaceHold {
  @Test
  public void failedAssumptionsMeanIgnored() {
    Result result = JUnitCore.runClasses(AssumptionTest.HasFailingAssumption.class);
    assertThat(result.getRunCount(), is(0));
    assertThat(result.getIgnoreCount(), is(1));
    assertThat(result.getFailureCount(), is(0));
  }
}
