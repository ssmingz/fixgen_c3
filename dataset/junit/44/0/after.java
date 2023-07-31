class PlaceHold {
  @Test
  public void shouldCreateFilter() throws Exception {
    Filter filter =
        filterFactoryFactory.createFilter(
            FilterFactoryFactoryTest.FilterFactoryStub.class,
            new FilterFactoryParams(createSuiteDescription(testName.getMethodName())));
    assertThat(filter, instanceOf(FilterFactoryFactoryTest.DummyFilter.class));
  }
}
