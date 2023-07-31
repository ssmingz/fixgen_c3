class PlaceHold {
  public synchronized void startup() throws DebugException {
    if (!isReady()) {
      List<OpenDefinitionsDocument> list = _model.getDefinitionsDocuments();
      for (int i = 0; i < list.size(); i++) {
        OpenDefinitionsDocument currDoc = list.get(i);
        currDoc.checkIfClassFileInSync();
      }
      _attachToVM();
      ThreadDeathRequest tdr = _eventManager.createThreadDeathRequest();
      tdr.setSuspendPolicy(EventRequest.SUSPEND_EVENT_THREAD);
      tdr.enable();
      EventHandlerThread eventHandler = new EventHandlerThread(this, _vm);
      eventHandler.start();
      _model.addListener(_watchListener);
    } else {
      throw new IllegalStateException("Debugger has already been started.");
    }
  }
}
