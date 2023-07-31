class PlaceHold {
  public String toString() {
    String result = "\n{ ";
    for (int i = 0; i < keys.size(); i++) {
      OptionParser key = keys.elementAt(i);
      result += ((key.name + " = ") + getString(key)) + '\n';
    }
    result += '}';
    return result;
  }
}
