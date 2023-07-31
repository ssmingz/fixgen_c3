class PlaceHold {
  public void tearDown() throws Exception {
    IOUtil.deleteOnExitRecursively(_parent);
    _auxFile.delete();
    _frame.dispose();
    _projFile = null;
    _model = null;
    _frame = null;
    super.tearDown();
  }
}
