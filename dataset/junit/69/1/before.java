class PlaceHold {
  @Test
  public void shouldCreateFilter() throws Exception {
    FilterFactoryParams params =
        new FilterFactoryParams(
            CategoryFilterFactoryTest.CategoryFilterFactoryStub.class.getName());
    Filter filter = categoryFilterFactory.createFilter(params);
    assertThat(filter, instanceOf(CategoryFilterFactoryTest.DummyFilter.class));
  }
}
