class PlaceHold {
  public boolean closeFileWithoutPrompt(final OpenDefinitionsDocument doc) {
    boolean found;
    synchronized (_documentsRepos) {
      found = _documentsRepos.remove(doc);
    }
    if (!found) {
      return false;
    }
    _documentNavigator.removeDocument(doc);
    _notifier.fileClosed(doc);
    doc.close();
    return true;
  }
}
