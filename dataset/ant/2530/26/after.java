class PlaceHold {
  public String toString() {
    final int size = params.size();
    final StringBuffer sb = new StringBuffer();
    boolean firstPass = true;
    for (int i = 0; i < size; i++) {
      if (!firstPass) {
        sb.append(" ,");
      }
      firstPass = false;
      final DnameParam param = ((DnameParam) (params.get(i)));
      sb.append(encode(param.getName()));
      sb.append('=');
      sb.append(encode(param.getValue()));
    }
    return sb.toString();
  }
}
