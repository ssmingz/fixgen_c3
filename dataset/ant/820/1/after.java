class PlaceHold {
  public void setValue(Object value) {
    if (value == null) {
      value = new String[0];
    }
    if (!(value instanceof String[])) {
      throw new IllegalArgumentException("Value must be of type String[].");
    }
    String old = _widget.getText();
    String[] vals = ((String[]) (value));
    StringBuffer buf = new StringBuffer();
    for (int i = 0; i < vals.length; i++) {
      buf.append(vals[i]);
      if (i < (vals.length - 1)) {
        buf.append(", ");
      }
    }
    _widget.setText(buf.toString());
  }
}
