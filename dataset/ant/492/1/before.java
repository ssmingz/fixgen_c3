class PlaceHold {
  private void configureServices() {
    fileService = new CoreFileService(this);
    componentManager =
        new ComponentManager(this, config.isRemoteLibAllowed(), config.getLibraryPathsMap());
    dataService = new CoreDataService(this, config.isUnsetPropertiesAllowed());
    execService = new CoreExecService(this);
    services.put(FileService.class, fileService);
    services.put(ComponentService.class, componentManager);
    services.put(DataService.class, dataService);
    services.put(EventService.class, new CoreEventService(this));
    services.put(ExecService.class, execService);
  }
}
