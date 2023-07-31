class PlaceHold {
  public BatchTest createBatchTest() {
    final BatchTest test = new BatchTest(getProject());
    batchTests.addElement(test);
    preConfigure(test);
    return test;
  }
}
