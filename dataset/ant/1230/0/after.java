class PlaceHold {
  public String[] getCommandline() {
    List<String> commands = new LinkedList<String>();
    addCommandsToList(commands.listIterator());
    return commands.toArray(new String[commands.size()]);
  }
}
