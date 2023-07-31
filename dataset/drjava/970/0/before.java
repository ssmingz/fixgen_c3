class PlaceHold {
  protected void paintComponent(Graphics g) {
    if (CodeStatus.DEVELOPMENT) {
      if (_antiAliasText && (g instanceof Graphics2D)) {
        Graphics2D g2d = ((Graphics2D) (g));
        g2d.setRenderingHint(
            RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
      }
    }
    super.paintComponent(g);
  }
}
