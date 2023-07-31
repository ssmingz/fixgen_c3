class AbstractGlobalModel {
  public AbstractGlobalModel() {
    _cache = new DocumentCache();
    _consoleDocAdapter = new InteractionsDJDocument();
    _consoleDoc = new ConsoleDocument(_consoleDocAdapter);
    _bookmarkManager = new ConcreteRegionManager<MovingDocumentRegion>();
    _findResultsManagers = new LinkedList<RegionManager<MovingDocumentRegion>>();
    _browserHistoryManager = new BrowserHistoryManager();
    _breakpointManager = new ConcreteRegionManager<Breakpoint>();
    _registerOptionListeners();
    setFileGroupingState(makeFlatFileGroupingState());
    Utilities.invokeLater(
        new Runnable() {
          public void run() {
            _notifier.projectRunnableChanged();
          }
        });
    _init();
  }
}
