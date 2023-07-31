class PlaceHold {
  public void removePendingRequest(DocumentDebugAction action) {
    Vector<DocumentDebugAction> actions = null;
    String className = action.getClassName();
    actions = _pendingActions.get(className);
    if (actions == null) {
      return;
    }
    actions.remove(action);
    if (actions.size() == 0) {
      _pendingActions.remove(className);
    }
  }
}
