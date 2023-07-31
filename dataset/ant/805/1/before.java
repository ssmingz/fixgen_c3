class PlaceHold {
  public Object convert(String value, Class type) throws ExecutionException {
    try {
      FileService fileService = ((FileService) (getContext().getCoreService(FileService.class)));
      return fileService.resolveFile(value);
    } catch (AntException e) {
      throw new ExecutionException("Unable to resolve file: " + value, e);
    }
  }
}
