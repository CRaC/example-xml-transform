<?xml version="1.0"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:fo="http://www.w3.org/1999/XSL/Format" version="1.0">
<xsl:output method="xml" version="1.0" indent="no" encoding="utf-8" omit-xml-declaration="no"/>

  <xsl:template match="/">
    Article - <xsl:value-of select="/Article/Title"/>
  </xsl:template>

</xsl:stylesheet>

