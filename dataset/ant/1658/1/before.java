class PlaceHold {
  public BatchTest createBatchTest() {
    BatchTest test = new BatchTest(getProject());
    batchTests.addElement(test);
    preConfigure(test);
    return test;
  }
}
