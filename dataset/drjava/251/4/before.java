class PlaceHold {
  public String format(Vector<T> v) {
    StringBuffer res = new StringBuffer(header);
    int size = v.size();
    int i = 0;
    while (i < size) {
      res.append(formatter.format(v.elementAt(i)));
      i++;
      if (i < size) {
        res.append(delim);
      }
    }
    return res.append(footer).toString();
  }
}
