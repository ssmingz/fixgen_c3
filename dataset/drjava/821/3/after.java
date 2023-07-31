class ConcreteOpenDefDoc {
  private ConcreteOpenDefDoc(File f, File dir, long stamp) {
    _file = f;
    _parentDir = dir;
    _classFile = FileOps.NULL_FILE;
    _timestamp = stamp;
    _image = null;
    _id = ID_COUNTER++;
    try {
      DDReconstructor ddr = makeReconstructor();
      _cacheAdapter = _cache.register(this, ddr);
    } catch (IllegalStateException e) {
      throw new UnexpectedException(e);
    }
    _breakpointManager = new SubsetRegionManager<Breakpoint>(this.getBreakpointManager());
    _bookmarkManager = new SubsetRegionManager<DocumentRegion>(this.getBookmarkManager());
    _findResultsManagers = new LinkedList<SubsetRegionManager<MovingDocumentRegion>>();
    for (RegionManager<MovingDocumentRegion> rm : this.getFindResultsManagers()) {
      addFindResultsManager(rm);
    }
    _browserHistoryManager =
        new SubsetRegionManager<DocumentRegion>(this.getBrowserHistoryManager());
  }
}
