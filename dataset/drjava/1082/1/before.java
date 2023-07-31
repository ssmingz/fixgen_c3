class PlaceHold {
  public void dispose() {
    _interpreterControl.killInterpreter(false);
    _notifier.removeAllListeners();
    synchronized (_documentsRepos) {
      _documentsRepos.clear();
    }
    _documentNavigator.clear();
  }
}
