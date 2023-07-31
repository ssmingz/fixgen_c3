class PlaceHold {
  public synchronized void shutdown() {
    if (!isReady()) {
      throw new IllegalStateException("Cannot shut down if debugger is not active.");
    }
    _model.removeListener(_watchListener);
    try {
      removeAllBreakpoints();
      removeAllWatches();
    } catch (DebugException de) {
      _log("Could not remove breakpoints/watches: " + de);
    }
    try {
      _vm.dispose();
    } catch (VMDisconnectedException vmde) {
    } finally {
      ((DefaultInteractionsModel) (_model.getInteractionsModel())).setToDefaultInterpreter();
      _vm = null;
      _eventManager = null;
      _suspendedThreads = new RandomAccessStack();
      _deadThreads = new DeadThreadFilter();
      _runningThread = null;
    }
  }
}
