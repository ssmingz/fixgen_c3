class PlaceHold {
  private void _setupFontPanel(ConfigPanel panel) {
    panel.addComponent(new FontOptionComponent(OptionConstants.FONT_MAIN, "Main Font", this));
    panel.addComponent(
        new FontOptionComponent(OptionConstants.FONT_DOCLIST, "Document List Font", this));
    panel.addComponent(new FontOptionComponent(OptionConstants.FONT_TOOLBAR, "Toolbar Font", this));
    panel.displayComponents();
  }
}
