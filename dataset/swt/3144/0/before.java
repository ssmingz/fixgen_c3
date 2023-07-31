class PlaceHold {
  public void setFlag(String flag, boolean value) {
    String[] flags = getFlags();
    HashSet set = new HashSet(Arrays.asList(flags));
    if (value) {
      set.add(flag);
    } else {
      set.remove(flag);
    }
    setFlags(((String[]) (set.toArray(new String[set.size()]))));
  }
}
