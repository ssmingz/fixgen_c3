class PlaceHold {
  public String flatten() {
    checkParams();
    StringBuffer buffer = new StringBuffer();
    Set set = params.keySet();
    String[] keys = ((String[]) (set.toArray(new String[set.size()])));
    Arrays.sort(keys);
    for (int j = 0; j < keys.length; j++) {
      String key = keys[j];
      Object value = params.get(key);
      String valueStr = "";
      if (value instanceof String) {
        valueStr = ((String) (value));
      } else if (value instanceof String[]) {
        String[] values = ((String[]) (value));
        StringBuffer valueBuffer = new StringBuffer();
        for (int i = 0; i < values.length; i++) {
          if (i != 0) {
            valueBuffer.append(" ");
          }
          valueBuffer.append(values[i]);
        }
        valueStr = valueBuffer.toString();
      } else {
        valueStr = value.toString();
      }
      if (valueStr.length() > 0) {
        if (buffer.length() != 0) {
          buffer.append(",");
        }
        buffer.append(key);
        buffer.append("=");
        String quote = "";
        if (valueStr.indexOf(',') != (-1)) {
          quote = (valueStr.indexOf('"') != (-1)) ? "'" : "\"";
        }
        buffer.append(quote);
        buffer.append(valueStr);
        buffer.append(quote);
      }
    }
    return buffer.toString();
  }
}
