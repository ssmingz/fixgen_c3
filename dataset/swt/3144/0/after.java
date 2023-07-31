class PlaceHold {
  public void setFlag(String flag, boolean value) {
    String[] flags = getFlags();
    HashSet<String> set = new HashSet<String>(Arrays.asList(flags));
    if (value) {
      set.add(flag);
    } else {
      set.remove(flag);
    }
    setFlags(set.toArray(new String[set.size()]));
  }
}
