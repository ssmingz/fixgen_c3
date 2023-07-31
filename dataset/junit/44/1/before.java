class PlaceHold {
  @Test
  public void shouldCreateFilterWithNoArguments() throws Exception {
    Filter filter =
        filterFactoryFactory.createFilterFromFilterSpec(
            FilterFactoryFactoryTest.FilterFactoryStub.class.getName());
    assertThat(filter, instanceOf(FilterFactoryFactoryTest.DummyFilter.class));
  }
}
