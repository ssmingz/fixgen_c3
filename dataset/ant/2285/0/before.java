class PlaceHold {
  public String[] getVariables() throws BuildException {
    List definitions = new LinkedList();
    ListIterator list = definitions.listIterator();
    addDefinitionsToList(list);
    if (definitions.size() == 0) {
      return null;
    } else {
      return ((String[]) (definitions.toArray(new String[definitions.size()])));
    }
  }
}
