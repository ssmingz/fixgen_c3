class PlaceHold {
  @Test
  public void shouldCreateFilter() throws Exception {
    Filter filter =
        FilterFactories.createFilter(
            FilterFactoriesTest.FilterFactoryStub.class, new FilterFactoryParams(""));
    assertThat(filter, instanceOf(FilterFactoriesTest.DummyFilter.class));
  }
}
