class PlaceHold {
  protected String getLabel() {
    if ((m_Label != null) && (m_Label.length() > 0)) {
      if (m_Label.length() > 31) {
        String label = m_Label.substring(0, 30);
        log("Label is longer than 31 characters, truncated to: " + label, MSG_WARN);
        return FLAG_LABEL + label;
      } else {
        return FLAG_LABEL + m_Label;
      }
    } else {
      return "";
    }
  }
}
