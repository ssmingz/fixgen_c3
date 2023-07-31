class PlaceHold {
  public void execute() throws ExecutionException {
    AntContext context = getContext();
    ComponentService componentService =
        ((ComponentService) (context.getCoreService(ComponentService.class)));
    componentService.loadLib(url.toString(), importAll);
  }
}
