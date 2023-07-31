class PlaceHold {
  @Test
  public void shouldCreateFilter() throws Exception {
    Filter filter =
        FilterFactories.createFilter(
            FilterFactoriesTest.FilterFactoryStub.class,
            new FilterFactoryParams(
                Description.createSuiteDescription(testName.getMethodName()), ""));
    assertThat(filter, instanceOf(FilterFactoriesTest.DummyFilter.class));
  }
}
