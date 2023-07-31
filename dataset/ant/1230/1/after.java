class PlaceHold {
  public String[] getVariables() throws BuildException {
    List<String> definitions = new LinkedList<String>();
    addDefinitionsToList(definitions.listIterator());
    if (definitions.size() == 0) {
      return null;
    } else {
      return definitions.toArray(new String[definitions.size()]);
    }
  }
}
