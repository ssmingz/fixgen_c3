class PlaceHold {
  @Test
  public void shouldThrowException() throws Exception {
    FilterFactoryParams params = new FilterFactoryParams("NonExistentFilter");
    expectedException.expect(FilterNotCreatedException.class);
    categoryFilterFactory.createFilter(params);
  }
}
