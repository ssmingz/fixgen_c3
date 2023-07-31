class PlaceHold {
  public void dispose() {
    _interpreterControl.killInterpreter(false);
    _notifier.removeAllListeners();
    synchronized (_documentsRepos) {
      _documentsRepos.clear();
    }
    try {
      Utilities.invokeAndWait(
          new Runnable() {
            public void run() {
              _documentNavigator.clear();
            }
          });
    } catch (InterruptedException e) {
      throw new UnexpectedException(e);
    }
  }
}
