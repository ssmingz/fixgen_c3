class PlaceHold {
  protected Embeddor getEmbeddor() throws Exception {
    if (m_embeddor == null) {
      Thread.currentThread().setContextClassLoader(getClass().getClassLoader());
      final Logger logger = getLogger();
      m_embeddor = new DefaultEmbeddor();
      m_embeddor.enableLogging(logger);
      final Parameters params = new Parameters();
      final File instDir = getHomeDirectory();
      params.setParameter("myrmidon.home", instDir.getAbsolutePath());
      m_embeddor.parameterize(params);
      m_embeddor.initialize();
      m_embeddor.start();
    }
    return m_embeddor;
  }
}
