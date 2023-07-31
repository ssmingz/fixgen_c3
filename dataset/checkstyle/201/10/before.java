class PlaceHold {
  @Override
  public final TreeSet<LocalizedMessage> process(File file, List<String> lines) {
    getMessageCollector().reset();
    if (Utils.fileExtensionMatches(file, fileExtensions)) {
      processFiltered(file, lines);
    }
    return getMessageCollector().getMessages();
  }
}
