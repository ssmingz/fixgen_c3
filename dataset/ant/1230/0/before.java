class PlaceHold {
  public String[] getCommandline() {
    List commands = new LinkedList();
    final ListIterator listIterator = commands.listIterator();
    addCommandsToList(listIterator);
    return ((String[]) (commands.toArray(new String[commands.size()])));
  }
}
