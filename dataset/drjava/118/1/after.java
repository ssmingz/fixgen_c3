class PlaceHold {
  public void testStarted(String testName) {
    try {
      _mainJVM.testStarted(testName);
    } catch (RemoteException re) {
      error.log(re);
    }
  }
}
