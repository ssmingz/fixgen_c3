class PlaceHold {
  public void execute() throws ExecutionException {
    AntContext context = getContext();
    ComponentService componentService =
        ((ComponentService) (context.getCoreService(ComponentService.class)));
    if (ref != null) {
      componentService.importFrameComponent(ref, alias);
    } else if (name == null) {
      componentService.importLibrary(libraryId);
    } else {
      componentService.importComponent(libraryId, name, alias);
    }
  }
}
