class PlaceHold {
  @Test
  public void shouldCreateFilter() throws Exception {
    Filter filter =
        filterFactoryFactory.createFilter(
            FilterFactoryFactoryTest.FilterFactoryStub.class, new NoFilterFactoryParams());
    assertThat(filter, instanceOf(FilterFactoryFactoryTest.DummyFilter.class));
  }
}
