class PlaceHold {
  public Object convert(String value, Class type) throws ConverterException {
    try {
      FileService fileService = ((FileService) (getContext().getCoreService(FileService.class)));
      return fileService.resolveFile(value);
    } catch (AntException e) {
      throw new ConverterException("Unable to resolve file: " + value, e);
    }
  }
}
