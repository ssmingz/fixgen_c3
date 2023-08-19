class PlaceHold {
  public void convert(Javadoc javadoc, BodyDeclaration bodyDeclaration) {
    if (bodyDeclaration.getJavadoc() == null) {
      if (javadoc != null) {
        if ((this.commentMapper == null)
            || (!this.commentMapper.hasSameTable(this.commentsTable))) {
          this.commentMapper = new DefaultCommentMapper(this.commentsTable);
        }
        Comment comment = this.commentMapper.getComment(javadoc.sourceStart);
        if (((comment != null) && comment.isDocComment()) && (comment.getParent() == null)) {
          org.eclipse.jdt.core.dom.Javadoc docComment =
              ((org.eclipse.jdt.core.dom.Javadoc) (comment));
          if (this.resolveBindings) {
            recordNodes(docComment, javadoc);
            // resolve member and method references binding
            Iterator tags = docComment.tags().listIterator();
            while (tags.hasNext()) {
              recordNodes(javadoc, ((TagElement) (tags.next())));
            }
          }
          bodyDeclaration.setJavadoc(docComment);
        }
      }
    }
  }
}
