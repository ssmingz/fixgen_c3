class PlaceHold {
  public void addPendingRequest(DocumentDebugAction action) {
    Vector<DocumentDebugAction> actions = null;
    String className = action.getClassName();
    actions = _pendingActions.get(className);
    if (actions == null) {
      actions = new Vector<DocumentDebugAction>();
      ClassPrepareRequest request = _manager.getEventRequestManager().createClassPrepareRequest();
      request.addClassFilter(className + "*");
      request.setSuspendPolicy(EventRequest.SUSPEND_EVENT_THREAD);
      request.enable();
    }
    actions.addElement(action);
    _pendingActions.put(className, actions);
  }
}
