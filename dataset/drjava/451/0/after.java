class PlaceHold {
  public void setDescription(String description) {
    _buttonPanel.setToolTipText(description);
    _noneButton.setToolTipText(description);
    _textButton.setToolTipText(description);
    _iconsButton.setToolTipText(description);
    _textAndIconsButton.setToolTipText(description);
    _label.setToolTipText(description);
  }
}
