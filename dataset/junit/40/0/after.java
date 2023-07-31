class FilterFactoryParams {
  public FilterFactoryParams(String args) {
    if (args == null) {
      throw new NullPointerException();
    }
    this.args = args;
  }
}
