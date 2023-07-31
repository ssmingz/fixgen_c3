class PlaceHold {
  public boolean closeFileWithoutPrompt(final OpenDefinitionsDocument doc) {
    boolean found;
    synchronized (_documentsRepos) {
      found = _documentsRepos.remove(doc);
    }
    if (!found) {
      return false;
    }
    try {
      Utilities.invokeAndWait(
          new Runnable() {
            public void run() {
              _documentNavigator.removeDocument(doc);
            }
          });
    } catch (InterruptedException e) {
      throw new UnexpectedException(e);
    }
    _notifier.fileClosed(doc);
    doc.close();
    return true;
  }
}
