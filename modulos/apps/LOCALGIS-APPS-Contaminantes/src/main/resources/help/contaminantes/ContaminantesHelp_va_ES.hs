<?xml version='1.0' encoding='ISO-8859-1' ?>
<!DOCTYPE helpset   
PUBLIC "-//Sun Microsystems Inc.//DTD JavaHelp HelpSet Version 1.0//EN"
         "http://java.sun.com/products/javahelp/helpset_1_0.dtd">

<helpset version="1.0">

  <!-- title -->
  <title>(VA)Ayuda - Gestor medioambiental</title>

  <!-- maps -->
  <maps>
     <homeID>geopistaIntro</homeID>
     <mapref location="ContaminantesMap_va.jhm"/>
  </maps>

  <!-- views -->
  <view mergetype="javax.help.UniteAppendMerge">
    <name>TOC</name>
    <label>(VA)Tabla de Contenidos</label>
    <type>javax.help.TOCView</type>
    <data>ContaminantesTOC_va.xml</data>
  </view>

  <view mergetype="javax.help.SortMerge">
    <name>Indice</name>
    <label>(VA)�ndice</label>
    <type>javax.help.IndexView</type>
    <data>ContaminantesIndex_va.xml</data>
  </view>
 
</helpset>

