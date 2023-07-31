class PlaceHold {
  private void configureServices() {
    fileService = new ExecutionFileService(this);
    componentManager = new ComponentManager(this, config.isRemoteLibAllowed());
    dataService = new ExecutionDataService(this, config.isUnsetPropertiesAllowed());
    services.put(FileService.class, fileService);
    services.put(ComponentService.class, componentManager);
    services.put(DataService.class, dataService);
  }
}
